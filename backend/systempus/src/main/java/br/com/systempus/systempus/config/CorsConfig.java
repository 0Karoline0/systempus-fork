package br.com.systempus.systempus.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig{

    @Bean
    public CorsFilter corsFilter(){
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(List.of("http://localhost:5173"));//Define a origem permitia
        config.setAllowedHeaders(List.of("*"));//Define os cabeçalhos permitidos
        config.setAllowedMethods(List.of("GET", "POST", "DELETE", "PUT", "PATCH"));//Define os métodos HTTP permitidos

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(("/**"), config);// "/**" é um padrão curinga que corresponde a qualquer URL

        return new CorsFilter(source);
    }
}