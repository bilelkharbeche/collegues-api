package dev.exemple.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import dev.exemple.filter.JWTAuthorizationFilter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${jwt.cookie}")
	private String TOKEN_COOKIE;

	@Autowired
	private JWTAuthorizationFilter jwtAuthorizationFilter;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// désactivation de la protection CSRF
		// non utilisée dans le cadre d'une Web API
		http.csrf().disable().authorizeRequests()
				// un GET /auth n'est pas soumise à authentification
				.antMatchers("/auth").permitAll().antMatchers("/page/auth").permitAll()
				// URL /admin/** ne sont accessible qu'aux rôles ADMIN
				.antMatchers("/admin").hasRole("ADMIN")
				// un GET /exemples n'est pas soumise à authentification
				.antMatchers(HttpMethod.GET, "/**").hasRole("USER")
				// un POST est soumise à authentification
				.antMatchers(HttpMethod.POST, "/**").hasRole("ADMIN")
				// un PATCH est soumise à authentification
				.antMatchers(HttpMethod.PATCH, "/**").hasRole("ADMIN")
				// accès à la console h2 sans authentification
				.antMatchers("/h2-console/**").permitAll()
				// Les autres requêtes sont soumises à authentification
				.anyRequest().authenticated()
				// accès à la console h2 sans authentification
				.and().headers().frameOptions().disable().and()
				.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class).logout()
				.logoutSuccessHandler((req, resp, auth) -> resp.setStatus(HttpStatus.OK.value()))
				.deleteCookies(TOKEN_COOKIE);

	}

}
