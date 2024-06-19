package wepik.backend.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000", "https://wepik.kr", "https://www.wepik.kr") // 요청을 허용하는 도메인
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowCredentials(true)
                        .allowedHeaders("*")
                        .maxAge(3600);
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //todo session 체크하는 경로 지정
                // 임시로 템플릿 생성만 session 체크
                registry.addInterceptor(new AuthenticationInterceptor())
                        .addPathPatterns("/template");
            }
        };
    }
}