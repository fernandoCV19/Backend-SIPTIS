package backend.siptis.auth.rutas;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthRutas {

    /*@Autowired
    AuthenticationManager authManager;
    @Autowired
    JWTokenUtils jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Credentials request) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getCorreo(), request.getContrasena())
            );

            Usuario user = (Usuario) authentication.getPrincipal();
            String accessToken = jwtUtil.createToken(user);
            AuthResponse response = new AuthResponse(user.getEmail(), accessToken);

            return ResponseEntity.ok().body(response);

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }*/
}
