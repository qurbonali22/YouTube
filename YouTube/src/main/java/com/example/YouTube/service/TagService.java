package com.example.YouTube.service;

import com.example.YouTube.dto.category.CategoryCreationDTO;
import com.example.YouTube.dto.tag.TagCreationDTO;
import com.example.YouTube.dto.tag.TagDTO;
import com.example.YouTube.entity.TagEntity;
import com.example.YouTube.enums.Language;
import com.example.YouTube.exception.CategoryNotFoundException;
import com.example.YouTube.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ResourceBundleService resourceBundleService;

    public TagDTO create(TagCreationDTO dto){

        TagEntity entity = new TagEntity();
        entity.setName(dto.getName());
        entity.setCreatedDate(LocalDateTime.now());

        tagRepository.save(entity);
        return toDTO(entity);
    }

    public Boolean update(Integer id, TagCreationDTO dto, Language language){

        TagEntity entity = get(id, language);
        entity.setName(dto.getName());
        tagRepository.save(entity);

        return true;
    }

    public Boolean delete(Integer id, Language language){

        TagEntity entity = get(id, language);
        tagRepository.delete(entity);

        return true;
    }

    public List<TagDTO> getList(){

        Iterable<TagEntity> entityList = tagRepository.findAll();
        List<TagDTO> dtoList = new LinkedList<>();

        for (TagEntity entity : entityList) {
            dtoList.add(toDTO(entity));
        }

        return dtoList;
    }

    public TagEntity get(Integer id, Language language){

        Optional<TagEntity> optional = tagRepository.findById(id);
        return optional.orElseThrow(() -> {
            throw new CategoryNotFoundException("Tag " + resourceBundleService.getMessage("item.not.found", language, id));
        });
    }

    public TagDTO toDTO(TagEntity entity){

        TagDTO dto = new TagDTO();
        dto.setName(entity.getName());
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());

        return dto;
    }
}
