package com.messenger.secure.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
 
	@Autowired
	@Qualifier("authUserDetailsService")
	private UserDetailsService authUserDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
            // all URLs are protected, except 'POST /login' so anonymous user can authenticate
            .authorizeRequests()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()

            // 401-UNAUTHORIZED when anonymous user tries to access protected URLs
            .and()
                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))

            // standard login form that sends 204-NO_CONTENT when login is OK and 401-UNAUTHORIZED when login fails
            .and()
                .formLogin()
                .successHandler((req, res, auth) -> res.setStatus(HttpStatus.NO_CONTENT.value()))
                .failureHandler(new SimpleUrlAuthenticationFailureHandler())

            // standard logout that sends 204-NO_CONTENT when logout is OK
            .and()
                .logout()
				.deleteCookies("JSESSIONID")
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.NO_CONTENT))
            
            .and()
                .csrf().disable() // à activer

            // // add CSRF protection to all URLs
            // .and()
            //     .csrf()
            //     .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        ;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
		authManagerBuilder.userDetailsService(authUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); //TODO: replace by new BCryptPasswordEncoder()
    }
	
}