package com.example.YouTube.config;

import com.example.YouTube.dto.jwt.JwtDTO;
import com.example.YouTube.service.CustomUserDetailsService;
import com.example.YouTube.util.JwtTokenUtil;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader("Authorization");

        //checking jwt exist or not exist
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        //checking jwt valid or invalid
        try {
            final String token = header.substring(7).trim();
            JwtDTO jwtDTO = JwtTokenUtil.decode(token); //token berilganda JwtTkenUtildagi decode da jwt vaqti o`tmaganmi
            // va boshqalarni tekshiradi

            String email = jwtDTO.getEmail();
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);


            UsernamePasswordAuthenticationToken
                    authentication = new UsernamePasswordAuthenticationToken(userDetails,
                    null, userDetails.getAuthorities());

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);
            return;

        } catch (JwtException | UsernameNotFoundException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setHeader("Message", "token not valid");
            return;
        }


    }
}
