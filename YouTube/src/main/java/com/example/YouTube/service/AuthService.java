package com.example.YouTube.service;

import com.example.YouTube.dto.LoginDTO;
import com.example.YouTube.dto.LoginResponseDTO;
import com.example.YouTube.dto.ProfileDTO;
import com.example.YouTube.entity.ProfileEntity;
import com.example.YouTube.enums.ProfileRole;
import com.example.YouTube.enums.ProfileStatus;
import com.example.YouTube.exception.EmailAlreadyExistException;
import com.example.YouTube.exception.LoginOrPasswordWrongException;
import com.example.YouTube.exception.StatusBlockException;
import com.example.YouTube.repository.ProfileRepository;
import com.example.YouTube.util.JwtTokenUtil;
import com.example.YouTube.util.MD5Util;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {
    private ProfileRepository profileRepository;
    private  MailService mailService;

    @Autowired
    public AuthService(ProfileRepository profileRepository, MailService mailService) {
        this.profileRepository = profileRepository;
        this.mailService = mailService;
    }

    public String registration(ProfileDTO dto) {

        Optional<ProfileEntity> byEmail = profileRepository.findByEmail(dto.getEmail());

        if (byEmail.isPresent()){
            ProfileEntity entity=byEmail.get();
            if (entity.getStatus().equals(ProfileStatus.NOT_ACTIVE)){
                profileRepository.delete(entity);
            }else {
                throw new EmailAlreadyExistException("Email already exists");
            }
        }

        ProfileEntity entity = new ProfileEntity();
        entity.setEmail(dto.getEmail());
        entity.setName(dto.getName());
        entity.setRole(ProfileRole.ROLE_USER);
        entity.setStatus(ProfileStatus.NOT_ACTIVE);
        entity.setSurname(dto.getSurname());
        entity.setPassword(MD5Util.MD5(dto.getPassword()));
        entity.setCreatedDate(LocalDateTime.now());
        profileRepository.save(entity);
        dto.setId(entity.getId());

         Thread thread = new Thread() {
            @Override
            public synchronized void start() {
                StringBuilder sb = new StringBuilder();
                sb.append("Salom Qalaysan \n");
                sb.append(" Bu test message");
                sb.append("Click the link:  http://localhost:8081/auth/verification/email/");
                sb.append(JwtTokenUtil.encode(dto.getEmail(),dto.getRole()));
                mailService.sendEmail(dto.getEmail(), "Complite Registration", sb.toString());
            }
        };
        thread.start();  //todo yuqorida threadda yozilgan vaqt olmaydi

//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                StringBuilder sb = new StringBuilder();
//                sb.append("<h1 style=\"text-align: center; background-color: indianred; color: white\"> Salom Mazgi qalaysan</h1>");
//                String link = String.format("<a style=\"text-align: left; background-color: aqua; background-image: -moz-repeating-radial-gradient(mediumpurple,lavender)\" href=\"http://localhost:8484/auth/verification/email/%s\"> Click there</a>", JwtUtil.encode(entity.getId()));
//                sb.append(link);
//                mailService.sendEmailMine(dto.getEmail(), "Complete Registration", sb.toString());
//            }
//        };
//        Thread thread = new Thread(runnable);
//        thread.start();

        return "Link was sent to your email !";
    }

    public String verification(String jwt) {

        String email;

        try {
            email= JwtTokenUtil.decodeEmail(jwt);
        } catch (JwtException e) {
            return "Verification failed";
        }

        Optional<ProfileEntity> optional = profileRepository.findByEmail(email);

        if (optional.isEmpty()) {
            return "Verification failed";
        }

        ProfileEntity entity = optional.get();

        if (!entity.getStatus().equals(ProfileStatus.NOT_ACTIVE)) {
            return "Verification failed";
        }
        entity.setStatus(ProfileStatus.ACTIVE);

        profileRepository.save(entity);
        return "Verification successfully done !!! ";
    }

    public LoginResponseDTO login(LoginDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.findByEmailAndPassword(dto.getEmail(), MD5Util.MD5(dto.getPassword()));
        if (optional.isEmpty()) {
            throw new LoginOrPasswordWrongException("Email or Password is incorrect !!!");
        }

        ProfileEntity entity = optional.get();
        if (entity.getStatus().equals(ProfileStatus.BLOCKED)) {
            throw new StatusBlockException("Profile status block");
        }
        LoginResponseDTO responseDTO = new LoginResponseDTO();
        responseDTO.setName(entity.getName());
        responseDTO.setSurname(entity.getSurname());
        responseDTO.setRole(entity.getRole());
        responseDTO.setToken(JwtTokenUtil.encode(entity.getEmail(), entity.getRole()));

        return responseDTO;
    }
}
