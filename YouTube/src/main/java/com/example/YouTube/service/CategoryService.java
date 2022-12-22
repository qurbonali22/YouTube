package com.example.YouTube.service;

import com.example.YouTube.dto.category.CategoryCreationDTO;
import com.example.YouTube.dto.category.CategoryDTO;
import com.example.YouTube.entity.CategoryEntity;
import com.example.YouTube.enums.Language;
import com.example.YouTube.exception.CategoryNotFoundException;
import com.example.YouTube.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ResourceBundleService resourceBundleService;

    public CategoryDTO create(CategoryCreationDTO dto){

        CategoryEntity entity = new CategoryEntity();
        entity.setName(dto.getName());
        entity.setCreatedDate(LocalDateTime.now());

        categoryRepository.save(entity);
        return toDTO(entity);
    }

    public Boolean update(Integer id, CategoryCreationDTO dto, Language language){

        CategoryEntity entity = get(id, language);
        entity.setName(dto.getName());
        categoryRepository.save(entity);

        return true;
    }

    public Boolean delete(Integer id, Language language){

        CategoryEntity entity = get(id, language);
        categoryRepository.delete(entity);

        return true;
    }

    public List<CategoryDTO> getList(){

        Iterable<CategoryEntity> entityList = categoryRepository.findAll();
        List<CategoryDTO> dtoList = new LinkedList<>();

        for (CategoryEntity entity : entityList) {
            dtoList.add(toDTO(entity));
        }

        return dtoList;
    }

    public CategoryEntity get(Integer id, Language language){

        Optional<CategoryEntity> optional = categoryRepository.findById(id);
        return optional.orElseThrow(() -> {
            throw new CategoryNotFoundException("Category " + resourceBundleService.getMessage("item.not.found", language, id));
        });
    }

    public CategoryDTO toDTO(CategoryEntity entity){

        CategoryDTO dto = new CategoryDTO();
        dto.setName(entity.getName());
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());

        return dto;
    }
}
