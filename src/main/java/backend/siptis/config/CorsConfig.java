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


                String localUI = "http://localhost:5173";

                registry.addMapping("/login")
                        .allowedOrigins(localUI)
                        .allowedMethods("*")
                        .exposedHeaders("*");

                registry.addMapping("/user/login")
                        .allowedOrigins(localUI)
                        .allowedMethods("*")
                        .exposedHeaders("*");


                registry.addMapping("/user/register/admin")

                        .allowedOrigins(localUI)
                        .allowedMethods("*")
                        .exposedHeaders("*");

                registry.addMapping("/user/register/student")
                        .allowedOrigins(localUI)
                        .allowedMethods("*")
                        .exposedHeaders("*");

                registry.addMapping("/user/test")

                        .allowedMethods("*")
                        .exposedHeaders("*");

                registry.addMapping("/todos")
                        .allowedOrigins(localUI)
                        .allowedMethods("*")
                        .exposedHeaders("*");

                registry.addMapping("/user/todos")
                        .allowedOrigins(localUI)
                        .allowedMethods("*");

                registry.addMapping("/user/information/*")
                        .allowedOrigins(localUI)
                        .allowedMethods("*");

                registry.addMapping("/user/information")
                        .allowedOrigins(localUI)
                        .allowedMethods("*");

                registry.addMapping("/user/editUser/*")
                        .allowedOrigins(localUI)
                        .allowedMethods("*");

                registry.addMapping("/email/askemail/*")
                        .allowedOrigins(localUI)
                        .allowedMethods("*");

                registry.addMapping("/email/changePassword")
                        .allowedOrigins(localUI)
                        .allowedMethods("*");


            }
        };
    }
}
