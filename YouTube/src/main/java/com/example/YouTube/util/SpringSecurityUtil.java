package com.example.YouTube.util;

import com.example.YouTube.config.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SpringSecurityUtil {
    public String getCurrentUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return currentPrincipalName;
    }

    public static Integer getCurrentUserId(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails user= (CustomUserDetails) authentication.getPrincipal();
        return user.getId();
    }
}
