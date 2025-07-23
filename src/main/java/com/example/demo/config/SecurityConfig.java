package com.example.demo.config;

import com.example.demo.service.JwtFilter;
import com.example.demo.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final JwtFilter jwtFilter;
  private final UserDetailsServiceImpl userDetailsService;

  public SecurityConfig(JwtFilter jwtFilter, UserDetailsServiceImpl userDetailsService) {
    this.jwtFilter = jwtFilter;
    this.userDetailsService = userDetailsService;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.cors().and()
      .csrf(csrf -> csrf.disable())
      .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // Статус сессии — статeless
      .authorizeHttpRequests(auth -> auth
        .requestMatchers(
          "/api/auth/**",
          "/api/users/**",
          "/api/styles/**",
          "/api/types/**",
          "/api/categories/**",
                  "/avatars/**", "/api/admin/products", "/api/products/**", "/api/cart/**"
        ).permitAll()
        .requestMatchers("/api/admin/**", "/api/users/{id}/role", "/api/users").hasRole("ADMIN")
            .requestMatchers("/api/users/me").authenticated()
            .requestMatchers("/api/users/lang").authenticated()
            .requestMatchers("/api/wishlist/**").authenticated()
            .requestMatchers("/api/users/theme").authenticated().requestMatchers("/api/users/**").authenticated()
        .anyRequest().authenticated()
      )
      .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
      .build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(userDetailsService);
    provider.setPasswordEncoder(passwordEncoder());
    return provider;
  }
  
//   @Bean
// public CorsConfigurationSource corsConfigurationSource() {
//     CorsConfiguration configuration = new CorsConfiguration();
//     configuration.addAllowedOrigin("https://ryu-no-kinu.netlify.app");  // адрес твоего React-фронтенда
//     configuration.addAllowedMethod("*");  // все HTTP методы, включая PATCH, OPTIONS
//     configuration.addAllowedHeader("*");  // все заголовки, в том числе Content-Type и Authorization
//     configuration.setAllowCredentials(true);

//     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//     source.registerCorsConfiguration("/api/**", configuration);
//     return source;
// }
}