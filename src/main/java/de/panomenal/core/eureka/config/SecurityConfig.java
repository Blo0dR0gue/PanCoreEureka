package de.panomenal.core.eureka.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true // Allow role check on method entry
)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> {
            try {
                csrf
                        .disable()
                        .authorizeHttpRequests(requests -> requests
                                .anyRequest()
                                .authenticated());
            } catch (Exception e) {
                e.printStackTrace();
            }
        })
                .httpBasic(withDefaults());
        return http.build();
    }
}
