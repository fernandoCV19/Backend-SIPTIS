package backend.siptis.auth.security;

import backend.siptis.auth.jwt.JWTAuthenticationFilter;
import backend.siptis.auth.jwt.JWTAuthorizationFilter;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig {
    private UserDetailsService userDetailsService;
    private JWTAuthorizationFilter jwtAuthorizationFilter;



    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager manager)
            throws Exception {
        JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
        jwtAuthenticationFilter.setAuthenticationManager(manager);
        //jwtAuthenticationFilter.setFilterProcessesUrl("/login");

        return http.cors().and()
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/user/register/student", "/user/register/admin", "/user/test", "/user/information/*",
                        "/user/register/teacher", "/user/editTeacher/*",
                        "/user/login", "/user/todos", "/user/editUser/*",
                        "/user/register/student", "/user/register/admin","/user/personal-activities/*",
                        "/user/buscarUser/**", "/user/personalInformation",
                        "/user/updateAreas/**", "/user/refreshtoken","/user/**",
                        "/user/updateAreas/**",
                        "/user/project/**",
                        "/user/project/*",

                        "/role/**",

                        "/document/**",

                        "/tribunal/**", "/teacher/**", "/tutor/**",

                        "/project/**", "/presentation/**", "/placesToDefense/**",

                        "/supervisor/**",
                        "/siptis/**", "/stadistics/**",

                        "/email",
                        "/email/send",
                        "/email/changePassword", "/email/askemail/*",
                        "/email/prueba/*",

                        "/general-activity", "/general-activity/create","/general-activity/*",

                        "/activity", "/activity/create","/activity/*",

                        "/cloud/**",
                        "/modality/**",

                        "/userArea/**",
                        "/area/**", "/subarea/**",
                        "/semester/**",
                        "/supervisor/**",

                        "/phase/**",
                        "/wpp")
                .permitAll()
                .anyRequest().authenticated()
                .and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint())
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilter(jwtAuthenticationFilter)
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    @Bean
    AuthenticationManager authenticationManager(HttpSecurity http,
                                                PasswordEncoder passwordEncoder) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService).passwordEncoder(passwordEncoder())
                .and().build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("mavl");
        System.out.println(password);
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint(){
        return new CustomAuthenticationEntryPoint();
    }
}
