package com.freshvotes.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfiguration   {

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Configuration
    public class SecurityConfiguration {
        @Bean
        public InMemoryUserDetailsManager userDetailsService() {
            UserDetails user = User.builder()
                    .username("user")
                    .password(getPasswordEncoder().encode("password"))
                    .roles("USER")
                    .build();
            return new InMemoryUserDetailsManager(user);
        }
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest().hasRole("USER").and()
                .formLogin().loginPage("/login")
                .defaultSuccessUrl("/dashboard")
                .permitAll().and()
                .logout().logoutUrl("/logout").permitAll();
        return http.build();
    }
}
