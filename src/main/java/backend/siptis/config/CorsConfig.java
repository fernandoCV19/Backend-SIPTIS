package backend.siptis.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {

                String userFrontend = "http://127.0.0.1:5173/";


                registry.addMapping("/user/login")
                        .allowedOrigins(userFrontend)
                        .allowedMethods("*")
                        .exposedHeaders("*");


                registry.addMapping("/user/register/admin")
                        .allowedOrigins(userFrontend)
                        .allowedMethods("*")
                        .exposedHeaders("*");

                registry.addMapping("/user/test")
                        .allowedOrigins(userFrontend)
                        .allowedMethods("*")
                        .exposedHeaders("*");

                registry.addMapping("/todos")
                        .allowedOrigins(userFrontend)
                        .allowedMethods("*");
            }
        };
    }
}
