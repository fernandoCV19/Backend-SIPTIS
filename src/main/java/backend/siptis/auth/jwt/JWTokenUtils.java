package backend.siptis.auth.jwt;

import backend.siptis.auth.entity.Role;
import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.service.userData.UserInformationService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTokenUtils {

    private static final long EXPIRE_TIME_DURATION = 12 * 60 * 60 * 1000; // hora.
    private static final long EXPIRE_TIME_DURATION_2 = 12 * 60 * 60 * 1000; // hora.
    private static final long REFRESH_TOKEN_EXPIRE_TIME_DURATION = 60 * 60 * 1000; //1 horas
    private static final Logger logger = LoggerFactory.getLogger(JWTokenUtils.class);
    private static final String ACCESS_TOKEN_SECRET = "$2a$12$JTfIoPcl28jeEFio3aHBa.rcqtBUgvykiKYgKxvikVzzxVAt82CEu\n";


    public static String createToken(UserInformationService.UserDetailImp userDI) {
        Date fechaExpiracion = new Date(System.currentTimeMillis() + EXPIRE_TIME_DURATION);

        return Jwts.builder().setSubject(userDI.getUsername())
                .setExpiration(fechaExpiracion)
                .claim("id", userDI.getId())
                .claim("projects", userDI.getProjects())
                .claim("roles", userDI.getRoles())
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();
    }

    public static String createToken(SiptisUser user) {
        UserInformationService.UserDetailImp userDI = new UserInformationService.UserDetailImp(user);
        Date fechaExpiracion = new Date(System.currentTimeMillis() + EXPIRE_TIME_DURATION_2);

        return Jwts.builder().setSubject(user.getEmail())
                .setExpiration(fechaExpiracion)
                .claim("id", userDI.getId())
                .claim("projects", userDI.getProjects())
                .claim("roles", userDI.getRoles())
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();
    }

    public static UserDetails getUserDetails(String token) {
        SiptisUser siptisUserDetails = new SiptisUser();
        Claims claims = getClaims(token);
        //String subject =claims.getSubject();
        String subject = (String) claims.get(Claims.SUBJECT);
        String roles = (String) claims.get("roles");

        roles = roles.replace("[", "").replace("]", "");
        String[] roleNames = roles.split(",");

        for (String aRoleName : roleNames) {

            aRoleName = aRoleName.trim();
            System.out.println("rol actual: " + aRoleName);
            siptisUserDetails.addRol(new Role(aRoleName));

            siptisUserDetails.setEmail(subject);
        }

        return siptisUserDetails;
    }

    public static Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                .build().parseClaimsJws(token).getBody();
    }

    public static Long getId(String token) {
        Claims claims = getClaims(token);

        Integer jwtId = (Integer) claims.get("id");
        Long id = Long.valueOf(jwtId);
        return id;
    }

    public static boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                    .build().parseClaimsJws(authToken).getBody();
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
            //throw new RefreshTokenException("El Refresh Token expiró");
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                    .build().parseClaimsJws(token).getBody();

            UserDetails userDetails = getUserDetails(token);

            //String correo =claims.getSubject();

            return new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()
            );
        } catch (Exception e) {
            return null;
        }
    }


}
