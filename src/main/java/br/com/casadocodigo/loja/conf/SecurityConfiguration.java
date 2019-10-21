package br.com.casadocodigo.loja.conf;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@EnableWebMvcSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter  {

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/css/**", "/js/**").permitAll()
		.antMatchers("/resources/**").permitAll()
		.antMatchers("/produtos/form").permitAll()
		.antMatchers("/carrinho").permitAll()
		.antMatchers(HttpMethod.POST, "/produtos").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET,"/produtos").permitAll()
		.antMatchers("/produtos/**").permitAll()
		.antMatchers("/").permitAll()
		.anyRequest().authenticated()
		.and().formLogin();
		
	}
	
}
