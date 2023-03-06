package BackendSIPTIS.auth.jwt;

import BackendSIPTIS.auth.service.UserDetailImp;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


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




}
