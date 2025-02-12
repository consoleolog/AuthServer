package com.example.authserver.v1.global.config;

import com.example.authserver.v1.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @FileName		: SecurityConfig.java
 * @Author			: ACR
 * @Date			: 25. 2. 12.
 * @Description		: 스프링 시큐리티 설정
 **/
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomOAuth2UserService userService;

    /**
     * @MethodName		: filterChain
     * @Author			: ACR
     * @Description		: 보안 관련 설정
     * @return : SecurityFilterChain
     **/
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests((authorize) ->
                authorize.requestMatchers("/**").permitAll()
        );
        http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.formLogin(form -> {
//           form.successHandler()
        });
        http.oauth2Login(oauth2->{
            oauth2.userInfoEndpoint(point -> {
                point.userService(userService);
            });
        });
        return http.build();
    }

    /**
     * @MethodName		: passwordEncoder
     * @Author			: ACR
     * @Description		: 비밀번호 해싱에 사용
     * @return : PasswordEncoder
     **/
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
