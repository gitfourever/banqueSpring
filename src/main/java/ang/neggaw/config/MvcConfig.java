package ang.neggaw.config;

import org.springframework.web.servlet.config.annotation.*;

//@Configuration
//@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/login").setViewName("seConnecter");
        registry.addViewController("/logout").setViewName("logout");
        registry.addViewController("/").setViewName("seConnecter");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200");
    }


}
