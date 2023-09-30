package com.tamarana.sistema.services.authentication;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginInterceptorAppConfig implements WebMvcConfigurer  {

    // Intercepta todas as requsicoes, menos as excluidas
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
            .excludePathPatterns(
                "/login",
                "/error",
                "/logar",
                "/js/**",
                "/css/**",
                "/img/**",
                "/cadastro",
                "/cadastrar",
                "/admin",
                "/adminLogar",
                "/",
                "/home",
                "/perfil",
                "/criacaoVaca"
                
            );
    }
    
}
