package rw.ehealth.config;

import java.security.SecureRandom;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import rw.ehealth.service.user.UserSecurityService;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AccessDeniedHandler accessDeniedHandler;

	@Autowired
	private UserSecurityService userSecurityService;

	private static final String SALT = "salt";

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12, new SecureRandom(SALT.getBytes()));
	}
	
	 private static final String[] PUBLIC_MATCHERS = { "/bower_components/**", "/dist/**", "/plugins/**", "/js/**",
		"/build/**" };
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(PUBLIC_MATCHERS).permitAll()
				.antMatchers("/confirm**", "/forgot-password**", "/reset-password**","/home","/hospital","/admission","/users","/patient","/newuser", "/register/**").permitAll()
				.antMatchers("/ForDoctors/**").hasAnyRole("DOCTOR").antMatchers("/admin/**").hasRole("ADMIN").anyRequest()
				.authenticated();

		http.csrf().disable().cors().disable().formLogin().failureUrl("/login?error").defaultSuccessUrl("/")
				.loginPage("/login").permitAll().and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
				.deleteCookies("remember-me").permitAll().and().rememberMe().and().exceptionHandling()
				.accessDeniedHandler(accessDeniedHandler);
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// auth.inMemoryAuthentication().withUser("user").password("password").roles("USER"); //This is in-memory
		// authentication
		auth.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
	}
}