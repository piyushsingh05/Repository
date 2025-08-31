package com.org.newsAggregatorAPI.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class newsConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder(11);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        authorizeRequest -> authorizeRequest.requestMatchers("/api/register")
                                .permitAll()
                                .anyRequest()
                                .authenticated())
                .formLogin(formLogin->formLogin.defaultSuccessUrl("/hey", true).permitAll())
                .addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class);
                return httpSecurity.build();
    }
}
