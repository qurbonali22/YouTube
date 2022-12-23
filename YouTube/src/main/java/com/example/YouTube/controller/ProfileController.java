package com.example.YouTube.controller;

import com.example.YouTube.dto.ProfileDTO;
import com.example.YouTube.entity.ProfileEntity;
import com.example.YouTube.service.ProfileService;
import com.example.YouTube.util.SpringSecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
@Tag(name = "ProfileController API", description = "Api list for Profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;


    @PreAuthorize("hasAnyRole('ADMIN','USER','OWNER')")
    @PutMapping("/changePassword/{password}")
    @Operation(summary = " change Password", description = "Method used for change password of Profile")
    public ResponseEntity<?> changePassword(@PathVariable("password") String password){
        String result=profileService.changePassword(password, SpringSecurityUtil.getCurrentUserId());
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER','OWNER')")
    @PutMapping("/changeEmail/{email}")
    public ResponseEntity<?> changeEmail(@PathVariable("email") String email){
        String result=profileService.changeEmail(email, SpringSecurityUtil.getCurrentUserId());
        return ResponseEntity.ok(result);
    }

    //todo attach

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProfileDTO dto) {
        ProfileDTO result=profileService.createProfile(dto);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER','OWNER')")
    @GetMapping("/getProfile/{email}")
    public ResponseEntity<?> getProfile(@PathVariable("email") String email){
        ProfileDTO result=profileService.getProfile(email);
        return ResponseEntity.ok(result);
    }



}
