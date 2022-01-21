package pl.pabjan.schoolmanagementsystem.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.pabjan.schoolmanagementsystem.config.security.filter.AuthenticationFilter;
import pl.pabjan.schoolmanagementsystem.config.security.filter.AuthorizationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final String secret;
    private final long tokenExpirationTime;

    public SecurityConfig(UserDetailsService userDetailsService, @Value("${jwt.secret}") String secret, @Value("${jwt.expirationTime}") long tokenExpirationTime) {
        this.userDetailsService = userDetailsService;
        this.secret = secret;
        this.tokenExpirationTime = tokenExpirationTime;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String loginUrl = "/api/auth/login";
        http.cors().and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(STATELESS)
                .and()
                .authorizeRequests().antMatchers("/api/auth/**")
                .permitAll()
                .and()
                .authorizeRequests().antMatchers("/api/user/**", "/api/admin/**").hasRole("ADMIN")
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .addFilter(new AuthenticationFilter(loginUrl, authenticationManagerBean(), secret, tokenExpirationTime))
                .addFilter(new AuthorizationFilter(authenticationManagerBean(), userDetailsService, secret)).exceptionHandling()
                .and()
                .exceptionHandling();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
