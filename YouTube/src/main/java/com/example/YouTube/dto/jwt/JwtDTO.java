package com.example.YouTube.dto.jwt;

import com.example.YouTube.enums.ProfileRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtDTO {
    private String email;
    private ProfileRole role;
}