package backend.siptis.auth.security;

import backend.siptis.auth.jwt.JWTAuthenticationFilter;
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
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
                .requestMatchers("/user/**", "/user/register/student", "/user/register/admin" ,
                        "/user/register/teacher", "/user/editTeacher/*",
                        "/user/test", "/user/login","/user/todos","/user/editUser/*",
                        "/user/information/*", "/email/send", "/general-activity",
                        "/general-activity/create","/email/prueba/*", "/general-activity/*",
                        "/email/changePassword","/email/askemail/*",
                        "/user/information/*", "/user/personal-activities/*", "/email/send",
                         "/general-activity","/general-activity/create", "/general-activity/*",
                         "/user/register/student", "/user/register/admin", "/user/test", "/supervisor/**",
                          "/tribunal/**", "/teacher/**", "/tutor/**", "/project/**", "/email",
                        "/general-activity", "/general-activity/create", "/general-activity/*", "/user/test",
                         "/user/login", "/user/todos", "/user/editUser/*",
                        "/user/information/*", "/email/send", "/project/**", "/presentation/**"
                        ,"/document/**", "/cloud/**", "/user/buscarUser/**, /personalInformation")
                .permitAll()
                .anyRequest().authenticated()
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
}
