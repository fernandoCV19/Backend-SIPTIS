package BackendSIPTIS.auth.jwt;

import org.springframework.security.core.userdetails.UserDetails;

public class JWTokenUtils {

    private static final long EXPIRE_TIME_DURATION = 2 * 60 * 60* 1000; //2horas.
    private static final String ACCESS_TOKEN_SECRET= "abcdefghijklmnOPQRSTUVWXYZ";

    public static String createToken(){
        return null;
    }




}
