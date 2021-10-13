package com.gualberto.ronei.rmgschoolapi.infra.config;

import com.gualberto.ronei.rmgschoolapi.infra.authentication.AuthenticatedUserService;
import com.gualberto.ronei.rmgschoolapi.infra.authentication.AuthenticationFilter;
import com.gualberto.ronei.rmgschoolapi.infra.authentication.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private final AuthenticatedUserService authenticatedUserService;

    private final TokenService tokenService;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authenticatedUserService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/v1/account").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/auth/token").permitAll()
                .antMatchers("/api/v1/categories/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new AuthenticationFilter(authenticatedUserService, tokenService), UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/v3/api-docs/**", "/swagger-ui/**",
                        "/configuration/security", "/swagger-ui.html", "/webjars/**", "/resources/**", "/template/**", "/css/**", "/img/**", "/js/**")
                .antMatchers(HttpMethod.OPTIONS);
    }

}
