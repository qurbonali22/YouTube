package com.example.YouTube.service;

import com.example.YouTube.config.CustomUserDetails;
import com.example.YouTube.entity.ProfileEntity;
import com.example.YouTube.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
// find User by Email from DB
        Optional<ProfileEntity> optional = profileRepository.findByEmail(email);
        if (optional.isEmpty()) {
            throw new UsernameNotFoundException("Bad Credentials. Mazgi");
        }
        ProfileEntity profile = optional.get();
        return new CustomUserDetails(profile.getEmail(),profile.getId(),profile.getPassword(),profile.getRole());
    }


}
