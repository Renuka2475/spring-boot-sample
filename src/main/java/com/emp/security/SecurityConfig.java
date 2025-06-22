package com.emp.security;

import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServlet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
public
class SecurityConfig {

    @Bean
    public
    InMemoryUserDetailsManager userDetailsManager() {
        UserDetails john = User.builder()
                .username("John")
                .password("{noop}test")
                .roles("EMPLOYEE")
                .build();

        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}test")
                .roles("MANAGER")
                .build();

        UserDetails Susan = User.builder()
                .username("susan")
                .password("{noop}test")
                .roles("ADMIN")
                .build();
        UserDetails renuka = User.builder()
                .username("renuka")
                .password("{noop}test")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(john, mary, Susan, renuka);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configurer->
                configurer
                        .requestMatchers("/h2-console/**").permitAll()
                        //.requestMatchers(toH2Console()).permitAll()
                        .requestMatchers("/docs/**","/swagger-ui/**","/v3/api-docs/**","/swagger-ui.html").permitAll()
                       //.requestMatchers(HttpMethod.GET, "api/employee/all").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET,"/api/employee/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/employee/insert").hasRole("MANAGER")
                        //.requestMatchers(HttpMethod.PUT,"/api/employee/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE,"/api/employee/**").hasRole("ADMIN")

        ).headers(h -> h.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));

//        Use Http Basic Authentication
        http.httpBasic(httpBasicCutomizer -> httpBasicCutomizer.disable());
        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable());
      /*  http.exceptionHandling(exceptionHandling -> exceptionHandling
                .authenticationEntryPoint(authenticationEntryPoint()));*/

        return http.build();
    }

    @Bean

    public AuthenticationEntryPoint authenticationEntryPoint(){

        return (request, response, authException) -> {
//           Sending 401 Status without triggering a basic auth of browser
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");

//            removes WWW-Authenticate header to prevent browser pop up
            response.setHeader("WWW-Authenticate","");
            response.getWriter().write("{\"error\":\"Unauthorised access\"}");
        };

    }

}
