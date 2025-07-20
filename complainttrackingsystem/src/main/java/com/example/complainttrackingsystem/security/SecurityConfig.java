package com.example.complainttrackingsystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // ✅ Disable CORS & CSRF for same-origin simple setup
            .cors(cors -> cors.disable())
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth
                // ✅ Allow public HTML & static resources
                .requestMatchers(
                    "/", "/index.html",
                    "/signup.html", "/login.html", "/verify.html",
                    "/css/**", "/js/**", "/images/**"
                ).permitAll()

                // ✅ Open authentication & OTP APIs
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/otp/**").permitAll()

                // ✅ Complaint submission for public
                .requestMatchers("/api/complaints/create").permitAll()

                // ✅ User dashboard access
                .requestMatchers("/api/complaints/my").hasAnyRole("USER", "ADMIN")

                // ✅ Admin dashboard and data access
                .requestMatchers("/api/complaints/**").hasRole("ADMIN")
                .requestMatchers("/api/admin/**").hasRole("ADMIN")

                // ✅ User dashboard APIs
                .requestMatchers("/api/user/**").hasAnyRole("USER", "ADMIN")

                // ✅ Everything else needs login
                .anyRequest().permitAll()
            )

            // ✅ Use HTTP session
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            );

            // ✅ Logout handling
           /* .logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/api/logout"))
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/login.html")
            );*/

        return http.build();
    }
}
