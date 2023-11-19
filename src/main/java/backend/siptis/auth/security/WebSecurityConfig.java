package backend.siptis.auth.security;

import backend.siptis.auth.jwt.JWTAuthorizationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig {
    private static final String[] AUTH_WHITELIST = {
            "/api/v1/auth/**",
            "/v3/api-docs.yml",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html"
    };
    private UserDetailsService userDetailsService;
    private JWTAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager manager)
            throws Exception {

        return http.cors().and()
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/user/register/student", "/user/register/admin", "/user/test", "/user/information/*",
                        "/user/register/teacher", "/user/editTeacher/*",
                        "/user/login", "/user/todos", "/user/editUser/*",
                        "/user/register/student", "/user/register/admin", "/user/personal-activities/*",
                        "/user/buscarUser/**", "/user/personalInformation",
                        "/user/updateAreas/**", "/user/refreshtoken", "/user/**",
                        "/user/updateAreas/**",
                        "/user/project/**",
                        "/user/project/*",
                        "/schedule/**",
                        "/schedule/*",

                        "/role/**",

                        "/document/**",
                        "/document/**", "/document",

                        "/tribunal/**", "/teacher/**", "/tutor/**",

                        "/project/**", "/presentation/**", "/placesToDefense/**",

                        "/supervisor/**",
                        "/siptis/**", "/stadistics/**",

                        "/email",
                        "/email/send",
                        "/email/changePassword", "/email/askemail/*",
                        "/email/prueba/*",

                        "/general-activity", "/general-activity/create", "/general-activity/*",

                        "/activity", "/activity/create", "/activity/*",

                        "/cloud/**",
                        "/modality/**",

                        "/bot/**",

                        "/userArea/**",
                        "/area/**", "/subarea/**",
                        "/semester/**",
                        "/supervisor/**",

                        "/phase/**",
                        "/wpp",
                        "/defense/**")
                .permitAll()
                .requestMatchers(AUTH_WHITELIST).permitAll()
                .anyRequest().authenticated()
                .and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint())
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    @Bean
    AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService).passwordEncoder(passwordEncoder())
                .and().build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint();
    }
}
