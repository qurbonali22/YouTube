package com.example.YouTube.service;

import com.example.YouTube.dto.profile.ProfileDTO;
import com.example.YouTube.entity.ProfileEntity;
import com.example.YouTube.enums.ProfileStatus;
import com.example.YouTube.exception.EmailAlreadyExistException;
import com.example.YouTube.repository.ProfileRepository;
import com.example.YouTube.util.JwtTokenUtil;
import com.example.YouTube.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ProfileService {

    private ProfileRepository profileRepository;
    private  MailService mailService;

    @Autowired
    public ProfileService(ProfileRepository profileRepository, MailService mailService) {
        this.profileRepository = profileRepository;
        this.mailService = mailService;
    }

    public String changePassword(String password, Integer currentUserId) {
       ProfileEntity entity=getEntity(currentUserId);
       entity.setPassword(MD5Util.MD5(password));
       profileRepository.save(entity);
       return "password is chenged";
    }

    public String changeEmail(String email, Integer currentUserId) {
        ProfileEntity entity=getEntity(currentUserId);

        entity.setEmail(email);
        entity.setStatus(ProfileStatus.NOT_ACTIVE);
        profileRepository.save(entity);

        Thread thread = new Thread() {
            @Override
            public synchronized void start() {
                StringBuilder sb = new StringBuilder();
                sb.append("Hi !!!  \n");
                sb.append(" This is a verification message");
                sb.append("Click the link:  http://localhost:8081/auth/verification/email/");
                sb.append(JwtTokenUtil.encode(email,entity.getRole()));
                mailService.sendEmail(email, "Complite Registration", sb.toString());
            }
        };
        thread.start();

        return "Link was sent to your email !";
    }

    public ProfileDTO createProfile(ProfileDTO dto) {

        Optional<ProfileEntity> optional=profileRepository.findByEmail(dto.getEmail());

        if (optional.isPresent()){
            throw new EmailAlreadyExistException("email is already exist!!!");
        }

        ProfileEntity entity=new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setStatus(ProfileStatus.ACTIVE);
        entity.setRole(dto.getRole());
        entity.setPassword(MD5Util.MD5(dto.getPassword()));
        entity.setAttachId(dto.getAttachId());
        entity.setCreatedDate(LocalDateTime.now());

        profileRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public ProfileDTO getProfile(String email) {
        ProfileEntity entity=getEntityByEmail(email);

        ProfileDTO dto=new ProfileDTO();
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setEmail(entity.getEmail());
        dto.setAttach(dto.getAttach());
        dto.setCreatedDate(dto.getCreatedDate());
        dto.setRole(dto.getRole());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    public ProfileEntity getEntity(Integer profileId){
        Optional<ProfileEntity> optional=profileRepository.findById(profileId);
        return optional.get();
    }

    private ProfileEntity getEntityByEmail(String email) {
        Optional<ProfileEntity> optional=profileRepository.findByEmail(email);
        if (optional.isEmpty()){
            throw new NotFoundException("email is not exist!!!");
        }
        return optional.get();
    }
}
