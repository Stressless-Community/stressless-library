package com.stresslesslibrary.bookservice.configurations;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.crypto.factory.PasswordEncoderFactories;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.HttpStatusEntryPoint;


@Configuration
// @EnableWebSecurity
public class WebSecurityConfig{
	
	// @Bean
    // public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
    //     UserDetails user = User.withUsername("user")
    //         .password(passwordEncoder.encode("password"))
    //         .roles("USER")
    //         .build();

    //     UserDetails bienfait = User.withUsername("bienfaitluvako@gmail.com")
    //         .password(passwordEncoder.encode("library2023?"))
    //         .roles("USER", "ADMIN")
    //         .build();
		
	// 	UserDetails egide = User.withUsername("mushokoegide@gmail.com")
    //         .password(passwordEncoder.encode("library2023?"))
    //         .roles("USER", "ADMIN")
    //         .build();
		

    //     return new InMemoryUserDetailsManager(user, egide, bienfait);
    // }

    // @Bean
    // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        
    //   return http .authorizeHttpRequests( x -> x
    //     // .requestMatchers("/**").permitAll()
    //     // // .requestMatchers("/authors").permitAll()
    //     // // .requestMatchers("/login/**").permitAll()
    //     .anyRequest().permitAll()
    //     )
    //   .exceptionHandling(x -> x
    //   .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
    //   .build();
    // }

    // @Bean
    // public PasswordEncoder passwordEncoder() {
    //     PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    //     return encoder;
    // }

}
