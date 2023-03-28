package backend.siptis.auth.jwt;

import backend.siptis.auth.entity.Role;
import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.service.userData.UserDetailImp;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Date;

public class JWTokenUtils {

    private static final long EXPIRE_TIME_DURATION = 2 * 60 * 60* 1000; //2horas.
    private static final String ACCESS_TOKEN_SECRET= "$2a$12$JTfIoPcl28jeEFio3aHBa.rcqtBUgvykiKYgKxvikVzzxVAt82CEu\n";

    public static String createToken(UserDetailImp userDI){
        Date fechaExpiracion =new Date(System.currentTimeMillis() + EXPIRE_TIME_DURATION);

        return Jwts.builder().setSubject(userDI.getUsername())
                .setExpiration(fechaExpiracion)
                .claim("roles", userDI.getRoles())
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();
    }



    public static UserDetails getUserDetails(String token){
        SiptisUser siptisUserDetails =new SiptisUser();
        Claims claims = getClaims(token);
        //String subject =claims.getSubject();
        String subject = (String) claims.get(Claims.SUBJECT);
        String roles = (String) claims.get("roles");

        roles = roles.replace("[", "").replace("]", "");
        String[] roleNames = roles.split(",");

        for (String aRoleName : roleNames) {

            aRoleName = aRoleName.trim();
            System.out.println("rol actual: "+ aRoleName);
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

    public static UsernamePasswordAuthenticationToken getAuthentication(String token){
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                    .build().parseClaimsJws(token).getBody();

            UserDetails userDetails = getUserDetails(token);

            //String correo =claims.getSubject();

            return new UsernamePasswordAuthenticationToken(
                    userDetails, null,userDetails.getAuthorities()
            );
        }catch (Exception e){
            return null;
        }
    }


}
