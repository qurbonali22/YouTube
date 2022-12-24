package com.example.YouTube.controller;

import com.example.YouTube.dto.profile.ProfileDTO;
import com.example.YouTube.entity.AttachEntity;
import com.example.YouTube.service.ProfileService;
import com.example.YouTube.util.SpringSecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @Operation(summary = " change Email", description = "Method used for change Email of Profile")
    public ResponseEntity<?> changeEmail(@PathVariable("email") String email){
        String result=profileService.changeEmail(email, SpringSecurityUtil.getCurrentUserId());
        return ResponseEntity.ok(result);
    }
    //todo attach
//    @PreAuthorize("")
//    @PutMapping("/updateAttach/")
//    @Operation(summary = "update Attach", description = "Method used for update Attach")
//    public ResponseEntity<?> updateAttach(){
//        AttachEntity result=profileService.updateAttach();
//        return ResponseEntity.ok(result);
//    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    @Operation(summary = "Create Profile", description = "Method used for create Profile")
    public ResponseEntity<?> create(@RequestBody ProfileDTO dto) {
        ProfileDTO result=profileService.createProfile(dto);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER','OWNER')")
    @GetMapping("/getProfile/{email}")
    @Operation(summary = "get Profile", description = "Method used for get Profile by Email")
    public ResponseEntity<?> getProfile(@PathVariable("email") String email){
        ProfileDTO result=profileService.getProfile(email);
        return ResponseEntity.ok(result);
    }



}
