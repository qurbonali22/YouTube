package com.example.YouTube.service;

import com.example.YouTube.dto.profile.ProfileDTO;
import com.example.YouTube.dto.report.ReportCreationDTO;
import com.example.YouTube.dto.report.ReportDTO;
import com.example.YouTube.entity.ProfileEntity;
import com.example.YouTube.entity.ReportEntity;
import com.example.YouTube.enums.Language;
import com.example.YouTube.enums.ReportType;
import com.example.YouTube.exception.ItemNotFoundException;
import com.example.YouTube.repository.ReportRepository;
import org.springframework.beans.factory.ListableBeanFactoryExtensionsKt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private ProfileService profileService;

    public ReportDTO create(ReportCreationDTO dto, Integer profileId){

        ReportEntity entity = new ReportEntity();
        entity.setContent(dto.getContent());
        entity.setType(dto.getType());

        if (dto.getType().equals(ReportType.CHANNEL)){
            entity.setChannelId(dto.getEntityId());
        } else {
            entity.setVideoId(dto.getEntityId());
        }

        entity.setProfileId(profileId);
        entity.setCreatedDate(LocalDateTime.now());

        reportRepository.save(entity);

        return toDTO(entity);
    }

    public Page<ReportDTO> getList(Integer page, Integer size){

        Pageable pageable = PageRequest.of(page, size);

        Page<ReportEntity> obj = reportRepository.findAll(pageable);
        List<ReportEntity> entityList = obj.getContent();

        List<ReportDTO> dtoList = new LinkedList<>();

        for (ReportEntity entity : entityList) {
            dtoList.add(toDTO(entity));
        }

        return new PageImpl<>(dtoList, pageable, obj.getTotalElements());
    }

    public Boolean delete(Integer id, Language language){

        ReportEntity entity = get(id);
        reportRepository.delete(entity);
        return true;
    }

    public List<ReportDTO> getByProfileId(Integer profileId){

        List<ReportEntity> entityList = reportRepository.findByProfileId(profileId);
        List<ReportDTO> dtoList = new LinkedList<>();

        for (ReportEntity entity : entityList) {
            dtoList.add(toDTO(entity));
        }

        return dtoList;
    }

    public ReportEntity get(Integer id){
        Optional<ReportEntity> optional = reportRepository.findById(id);

        return optional.orElseThrow(() ->{
            throw new ItemNotFoundException("Report not found");
        });
    }



    public ReportDTO toDTO(ReportEntity entity){
        ReportDTO dto = new ReportDTO();
        dto.setContent(entity.getContent());
        dto.setType(entity.getType());

        if (entity.getType().equals(ReportType.CHANNEL)){
            dto.setEntityId(entity.getChannelId());
        } else {
            dto.setEntityId(entity.getVideoId());
        }

        dto.setCreatedDate(entity.getCreatedDate());
        ProfileEntity profile = profileService.getEntity(entity.getProfileId());
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setId(profile.getId());
        profileDTO.setName(profile.getName());
        profileDTO.setSurname(profile.getSurname());
//        photo...
        dto.setProfile(profileDTO);

        return dto;
    }
}
