package com.fox.gaea.configuration.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.fox.gaea.configuration.security.jwt.JwtAuthenticationEntryPoint;
import com.fox.gaea.configuration.security.jwt.JwtAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationEntryPoint unauthorizedHandler;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(c -> {
                    try {
                        c.init(http);
                    } catch (Exception e) {
                        throw new SecurityException(e);
                    }
                    c.disable();  // If you still want CSRF disabled, but in a more DSL styled manner
                })
                .authorizeHttpRequests(c -> {
                    //Allow this without a token
                    c.requestMatchers(HttpMethod.GET, "/static/**").permitAll();
                    c.requestMatchers(HttpMethod.GET, "/assets/**").permitAll();
                    c.requestMatchers(HttpMethod.GET, "/").permitAll(); // Allow root
                    c.requestMatchers(HttpMethod.GET, "/index.html").permitAll(); // Allow index.html
                    c.requestMatchers(HttpMethod.GET, "/static/**").permitAll();
                    c.requestMatchers(HttpMethod.GET, "/api/auth/**").permitAll();
                    c.requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll();
                    c.requestMatchers(HttpMethod.GET, "/api/news/**").permitAll();

                    c.anyRequest().authenticated();
                })
                .exceptionHandling(c -> c.authenticationEntryPoint(unauthorizedHandler))
                .sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}