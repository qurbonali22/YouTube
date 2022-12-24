package com.example.YouTube.dto.login;

import com.example.YouTube.enums.ProfileRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponseDTO {
    private String name;
    private String surname;
    private String email;
    private ProfileRole role;
    private String token;
}
