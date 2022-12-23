package com.example.YouTube.controller;

import com.example.YouTube.dto.login.LoginDTO;
import com.example.YouTube.dto.login.LoginResponseDTO;
import com.example.YouTube.dto.profile.ProfileDTO;
import com.example.YouTube.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

//    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_OWNER')")
    @PostMapping("/registration")
    ResponseEntity<?> registration(@RequestBody ProfileDTO dto){
        String result = authService.registration(dto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/verification/email/{jwt}")
    private ResponseEntity<?> verification(@PathVariable("jwt") String jwt) {
        String result = authService.verification(jwt);
        return ResponseEntity.ok(result);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO dto) {
        LoginResponseDTO response = authService.login(dto);
        return ResponseEntity.ok(response);
    }
}
