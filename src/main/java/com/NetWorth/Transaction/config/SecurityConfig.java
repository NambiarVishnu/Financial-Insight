package com.NetWorth.Transaction.config;


import com.NetWorth.Transaction.Service.CustomUserDetailService;
import com.NetWorth.Transaction.jwt.JwtAuthenticationFilter;
import org.apache.catalina.filters.HttpHeaderSecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {
@Autowired
private JwtAuthenticationFilter jwtAuthenticationFilter;

@Autowired
private CustomUserDetailService customUserDetailService;

@Bean
public PasswordEncoder passwordEncoder(){
    return  new BCryptPasswordEncoder();
}


@Bean
public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
}

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
    return http
            .csrf(AbstractHttpConfigurer::disable).cors(CorsConfigurer::disable)
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/auth/v1/register", "/auth/v1/login", "/upload-statement").permitAll()
                    .anyRequest().authenticated()
            )
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .httpBasic(Customizer.withDefaults())
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
}

//    http.csrf(csrf->csrf.disable())
//            .authorizeHttpRequests(auth->auth.requestMatchers("auth/register").permitAll()
//                    .anyRequest().authenticated()
//            ).sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            ).addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//
//    return http.build();
}

