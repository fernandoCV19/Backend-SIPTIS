package backend.siptis.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Value("${application.gateway.client}")
    private String localUI;

    @Bean
    public WebMvcConfigurer corsConfigurer() {

        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {

                registry.addMapping("/login")
                        .allowedOrigins(localUI)
                        .allowedMethods("*")
                        .exposedHeaders("*");

                registry.addMapping("/email/askemail/*")
                        .allowedOrigins(localUI)
                        .allowedMethods("*");

                registry.addMapping("/email/changePassword")
                        .allowedOrigins(localUI)
                        .allowedMethods("*");

                registry.addMapping("/v3/api-docs/**")
                        .allowedOrigins("*")
                        .allowedMethods("*");

                registry.addMapping("/swagger-ui/**")
                        .allowedOrigins("*")
                        .allowedMethods("*");

                registry.addMapping("/swagger-ui.html")
                        .allowedOrigins("*")
                        .allowedMethods("*");
            }
        };
    }
}
