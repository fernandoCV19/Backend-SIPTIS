package BackendSIPTIS.auth.jwt;

import BackendSIPTIS.auth.entity.Rol;
import BackendSIPTIS.auth.entity.Usuario;
import BackendSIPTIS.auth.service.UserDetailImp;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Date;
import java.util.Map;

public class JWTokenUtils {

    private static final long EXPIRE_TIME_DURATION = 2 * 60 * 60* 1000; //2horas.
    private static final String ACCESS_TOKEN_SECRET= "abcdefghijklmnOPQRSTUVWXYZ";

    public static String createToken(UserDetailImp usuarioI){
        Date fechaExpiracion =new Date(System.currentTimeMillis() + EXPIRE_TIME_DURATION);

        return Jwts.builder().setSubject(usuarioI.getUsername())
                .setExpiration(fechaExpiracion)
                .claim("roles", usuarioI.getRoles())
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();
    }



    public static UserDetails getUserDetails(String token){
        Usuario userDetails =new Usuario();
        Claims claims = getClaims(token);
        String subject =claims.getSubject();
        String roles = (String) claims.get("roles");

        roles = roles.replace("[", "").replace("]", "");
        String[] roleNames = roles.split(",");

        for (String aRoleName : roleNames) {

            aRoleName = aRoleName.trim();
            System.out.println("rol actual: "+ aRoleName);
            userDetails.addRol(new Rol(aRoleName));

            userDetails.setEmail(subject);
        }

        return userDetails;
    }

    public static Claims getClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                .build().parseClaimsJws(token).getBody();
    }


}
