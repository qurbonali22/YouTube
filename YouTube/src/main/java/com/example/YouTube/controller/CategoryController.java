package com.example.YouTube.controller;

import com.example.YouTube.dto.category.CategoryCreationDTO;
import com.example.YouTube.dto.category.CategoryDTO;
import com.example.YouTube.enums.Language;
import com.example.YouTube.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<CategoryDTO> save(@RequestBody CategoryCreationDTO dto){
        return ResponseEntity.ok(categoryService.create(dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> update(@PathVariable("id") Integer id,
                                              @RequestBody CategoryCreationDTO dto,
                   @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language language){

        return ResponseEntity.ok(categoryService.update(id, dto, language));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id,
                       @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language language){

        return ResponseEntity.ok(categoryService.delete(id, language));
    }


    @GetMapping("/getList")
    public ResponseEntity<List<CategoryDTO>> getList(){
        return ResponseEntity.ok(categoryService.getList());
    }
}
