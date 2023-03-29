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


                registry.addMapping("/login")
                        .allowedOrigins("http://127.0.0.1:5173/")
                        .allowedMethods("*")
                        .exposedHeaders("*");

                registry.addMapping("/user/register/admin")
                        .allowedOrigins("http://127.0.0.1:5173/")
                        .allowedMethods("*")
                        .exposedHeaders("*");

                registry.addMapping("/user/test")
                        .allowedOrigins("http://localhost:3000/")
                        .allowedMethods("*")
                        .exposedHeaders("*");

                registry.addMapping("/todos")
                        .allowedOrigins("http://localhost:3000/")
                        .allowedMethods("*");

                registry.addMapping("/presentation")
                        .allowedOrigins("http://127.0.0.1:5173/")
                        .allowedMethods("*")
                        .exposedHeaders("*");

                registry.addMapping("/proyecto/presentations")
                        .allowedOrigins("http://127.0.0.1:5173/")
                        .allowedMethods("*")
                        .exposedHeaders("*");
            }
        };
    }
}
