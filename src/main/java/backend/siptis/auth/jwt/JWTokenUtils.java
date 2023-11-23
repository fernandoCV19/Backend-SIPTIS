package backend.siptis.auth.jwt;

import backend.siptis.auth.entity.Role;
import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.exception.UserNotFoundException;
import backend.siptis.model.repository.auth.SiptisUserRepository;
import backend.siptis.service.user_data.UserInformationService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class JWTokenUtils {

    private static long expireTimeDuration;
    private static String accessTokenSecret;
    private static SiptisUserRepository siptisUserRepository;

    public JWTokenUtils(SiptisUserRepository siptisUserRepository) {
        JWTokenUtils.siptisUserRepository = siptisUserRepository;
    }

    public static String createToken(UserInformationService.UserDetailImp userDI) {
        Date expirationDate = new Date(System.currentTimeMillis() + expireTimeDuration);

        return Jwts.builder().setSubject(userDI.getUsername())
                .setExpiration(expirationDate)
                .claim("id", userDI.getId())
                .claim("projects", userDI.getProjects())
                .claim("roles", userDI.getRoles())
                .signWith(Keys.hmacShaKeyFor(accessTokenSecret.getBytes()))
                .compact();
    }


    public static UserDetails getUserDetails(String token) {
        SiptisUser siptisUserDetails = new SiptisUser();
        Claims claims = getClaims(token);
        String subject = (String) claims.get(Claims.SUBJECT);
        String roles = (String) claims.get("roles");

        Integer id = (Integer) claims.get("id");
        if (!siptisUserRepository.existsById(id.longValue())) {
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

    public static Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(accessTokenSecret.getBytes())
                .build().parseClaimsJws(token).getBody();
    }

    public static Long getId(String token) {
        Claims claims = getClaims(token);
        Integer jwtId = (Integer) claims.get("id");
        return Long.valueOf(jwtId);
    }

    public static List<?> getProjects(String token) {
        Claims claims = getClaims(token);

        return (List<?>) claims.get("projects");
    }

    public static boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(accessTokenSecret.getBytes())
                    .build().parseClaimsJws(authToken).getBody();
            return true;
            } catch (IllegalArgumentException e) {

            }
            return false;
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
        UserDetails userDetails = getUserDetails(token);
        return new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
    }

    @Value("${security.jwt.token.secret-key}")
    private void setAccessToken(String key) {
        accessTokenSecret = key;
    }

    @Value("${security.jwt.token.expire-length}")
    private void setExpireTime(String time) {
        expireTimeDuration = Long.parseLong(time);
    }


}
