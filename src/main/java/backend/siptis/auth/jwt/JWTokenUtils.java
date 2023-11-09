package backend.siptis.auth.jwt;

import backend.siptis.auth.entity.Role;
import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.exception.UserNotFoundException;
import backend.siptis.model.repository.userData.SiptisUserRepository;
import backend.siptis.service.userData.UserInformationService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@Component
public class JWTokenUtils {

    private static long EXPIRE_TIME_DURATION;
    private static String ACCESS_TOKEN_SECRET;

    private static SiptisUserRepository siptisUserRepository;

    public JWTokenUtils(SiptisUserRepository siptisUserRepository) {
        this.siptisUserRepository = siptisUserRepository;
    }

    @Value("${security.jwt.token.secret-key}")
    private void setAccessToken(String key){    ACCESS_TOKEN_SECRET = key;  }

    @Value("${security.jwt.token.expire-length}")
    private void setExpireTime(String time){   EXPIRE_TIME_DURATION  = Long.parseLong( time);  }


    public static String createToken(UserInformationService.UserDetailImp userDI){
        Date expirationDate =new Date(System.currentTimeMillis() + EXPIRE_TIME_DURATION);

        return Jwts.builder().setSubject(userDI.getUsername())
                .setExpiration(expirationDate)
                .claim("id", userDI.getId())
                .claim("projects", userDI.getProjects())
                .claim("roles", userDI.getRoles())
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();
    }

    public static String createToken(SiptisUser user){
        UserInformationService.UserDetailImp userDI = new UserInformationService.UserDetailImp(user);
        Date fechaExpiracion =new Date(System.currentTimeMillis() + EXPIRE_TIME_DURATION);

        return Jwts.builder().setSubject(user.getEmail())
                .setExpiration(fechaExpiracion)
                .claim("id", userDI.getId())
                .claim("projects", userDI.getProjects())
                .claim("roles", userDI.getRoles())
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();
    }

    public static UserDetails getUserDetails(String token) {
        SiptisUser siptisUserDetails =new SiptisUser();
        Claims claims = getClaims(token);
        String subject = (String) claims.get(Claims.SUBJECT);
        String roles = (String) claims.get("roles");

        Integer id = (Integer) claims.get("id");
        if(!siptisUserRepository.existsById(id.longValue())){
            throw new UserNotFoundException("USER NOT FOUND");
        }

        roles = roles.replace("[", "").replace("]", "");
        String[] roleNames = roles.split(",");
        for (String aRoleName : roleNames) {
            aRoleName = aRoleName.trim();
            siptisUserDetails.addRol(new Role(aRoleName));
            siptisUserDetails.setEmail(subject);
        }
        return siptisUserDetails;
    }

    public static Claims getClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                .build().parseClaimsJws(token).getBody();
    }

    public static Long getId(String token){
        Claims claims = getClaims(token);
        Integer jwtId = (Integer) claims.get("id");
        Long id = Long.valueOf(jwtId);
        return id;
    }

    public static ArrayList <?> getProjects(String token){
        Claims claims = getClaims(token);

        //Long id = Long.valueOf(jwtId)
        return (ArrayList <?>) claims.get("projects");
    }

    public static boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                    .build().parseClaimsJws(authToken).getBody();
            return true;
        } catch (IllegalArgumentException e) {
        }
        return false;
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                    .build().parseClaimsJws(token).getBody();
            UserDetails userDetails = getUserDetails(token);
            return new UsernamePasswordAuthenticationToken(
                    userDetails, null,userDetails.getAuthorities());
    }


}
