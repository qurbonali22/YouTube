package com.example.YouTube.util;

import com.example.YouTube.dto.jwt.JwtDTO;
import com.example.YouTube.enums.ProfileRole;
import io.jsonwebtoken.*;

import java.util.Date;

public class JwtTokenUtil {
    private static final int tokenLiveTime = 1000 * 3600 * 24; // 1-day bu jwt ni vaqti
    private static final String secretKey = "mazgi"; // shu yordamida encode qiladi


    public static String encode(String email, ProfileRole role) { //role va id dan hamda secret keydan foydalanib jwt yasayapti yani encode qilyapti
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setIssuedAt(new Date());
        jwtBuilder.signWith(SignatureAlgorithm.HS512, secretKey);

        jwtBuilder.claim("email", email);
        jwtBuilder.claim("role", role);

        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + (tokenLiveTime)));
        jwtBuilder.setIssuer("kunuz test portali");
        return jwtBuilder.compact();
    }


    public static JwtDTO decode(String token) {   //bu method yoqirida encodelangan jwt stringda qabul qiladi va uni decode qilib role va id ni aniqlab beradi
        JwtParser jwtParser = Jwts.parser();      //biz role va id ni qabul qiladigon JwtDTO ochamiz dtoda xoxlagan profileni xoxlagan fieldini olishimiz mumkin
        jwtParser.setSigningKey(secretKey);       //jwt ga ham xoxlaganimizi berishimiz mumkin u roleni o`zi yoki email ham bo`lishi mumkin

        Jws<Claims> jws = jwtParser.parseClaimsJws(token);

        Claims claims = jws.getBody();

        String email = (String) claims.get("email");

        String role = (String) claims.get("role");
        ProfileRole profileRole = ProfileRole.valueOf(role);

        return new JwtDTO(email, profileRole);
    }

    public static String decodeEmail(String jwt) {
        JwtParser jwtParser = Jwts.parser();      //biz role va id ni qabul qiladigon JwtDTO ochamiz dtoda xoxlagan profileni xoxlagan fieldini olishimiz mumkin
        jwtParser.setSigningKey(secretKey);       //jwt ga ham xoxlaganimizi berishimiz mumkin u roleni o`zi yoki email ham bo`lishi mumkin

        Jws<Claims> jws = jwtParser.parseClaimsJws(jwt);

        Claims claims = jws.getBody();

        String email = (String) claims.get("email");

        return email;
    }
}
