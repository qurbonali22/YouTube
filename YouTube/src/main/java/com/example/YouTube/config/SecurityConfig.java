package com.example.YouTube.config;

import com.example.YouTube.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.UUID;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtFilter jwtFilter;


    private AuthenticationEntryPoint authenticationEntryPoint;


    @Bean
    public AuthenticationProvider authenticationProvider() {
// authentication
//        String password = UUID.randomUUID().toString();
//        System.out.println("User Pasword mazgi: " + password);
//        UserDetails user = User.builder()
//                .username("user")
//                .password("{noop}" + password)
//                .roles("USER")
//                .build();
        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;

    }

    private static final String[] AUTH_WHITELIST = {
            "/v2/api-docs",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-resources",
            "/swagger-resources/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


// authorization
        http.csrf().disable().cors().disable();
        http.authorizeHttpRequests()
                .requestMatchers(AUTH_WHITELIST).permitAll()
                .requestMatchers("/profile/**").permitAll()
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/category/getList").permitAll()
                .requestMatchers("/tag/getList").permitAll()
                .requestMatchers("/tag/create").permitAll()
                .requestMatchers("/attach/upload").permitAll()
                .requestMatchers("/profile/**").hasRole("ADMIN")
                //todo 1ta * sleshdan keyin 1ta urli borlarni taniydi 2ta * sleshda ketyin
                //todo qancha url bo`lsa ham hammasini oladi
                .requestMatchers("/region/byLang/*").permitAll()
                .requestMatchers("region/*").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and().addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }
        };
    }

    public PasswordEncoder passwordEncoder() { // {noop}
        return NoOpPasswordEncoder.getInstance();
    }


}
