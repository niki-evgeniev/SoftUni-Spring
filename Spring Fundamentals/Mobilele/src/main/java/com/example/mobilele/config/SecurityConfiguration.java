package com.example.mobilele.config;

import com.example.mobilele.repository.UserRepository;
import com.example.mobilele.services.impl.MobileleUserDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    private final String rememberMeKey;

    public SecurityConfiguration(@Value("${mobilele.remember.me.key}")
                                 String rememberMeKey) {
        this.rememberMeKey = rememberMeKey;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(
                //url видими за усер
                authorizeRequest -> authorizeRequest
                        //всички статични ресурси могат да се видят-> js, images, css can see from anyone
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        // Всички могат да виждат логин, регистер и хоме
                        .requestMatchers("/", "/users/login", "/users/register", "/users/login-error").permitAll()
                        .requestMatchers("/offers/all", "/offers/add").permitAll()
                        .anyRequest().authenticated()

        ).formLogin(
                formLogin -> {
                    //redirect като достъпим нещо което не е достъпно
                    // предпочитана страница
                    formLogin
                            .loginPage("/users/login")
                            //това име "username" трябва да съвпада с името от HTML логин
                            .usernameParameter("username")
                            //това име "password" трябва да съвпада с името от HTML логин
                            .passwordParameter("password")
                            //ако се логнем успешно каде да отидем
                            .defaultSuccessUrl("/")
                            .failureForwardUrl("/users/login-error");
                }
        ).logout(
                logout -> {
                    logout.logoutUrl("/users/logout")
                            .logoutSuccessUrl("/")
                            .invalidateHttpSession(true);

                }
        ).rememberMe(
                rememberMe -> {
                    rememberMe
                            .key(rememberMeKey)
                            .rememberMeParameter("rememberme")
                            .rememberMeCookieName("rememberme");
                }
        );


        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new MobileleUserDetailsService(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }
}
