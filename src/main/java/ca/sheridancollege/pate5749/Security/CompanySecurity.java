package ca.sheridancollege.pate5749.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import ca.sheridancollege.pate5749.Handler.CompanyHandler;
import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class CompanySecurity {
	private CompanyHandler logDenied;
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http)
			throws Exception {
		http.csrf().disable();
		http.headers().frameOptions().disable();
		http.authorizeHttpRequests((authz) -> authz
				.requestMatchers("/**").permitAll()
				.requestMatchers("/h2-console/**").permitAll()
				.anyRequest().authenticated())
				.formLogin((formLogin) -> formLogin
				.loginPage("/login")
				.failureUrl("/login?failed")
				.permitAll())
				.logout((logout) -> logout
				.deleteCookies("remove")
				.invalidateHttpSession(true)
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login?logout")
				.permitAll())
				.exceptionHandling((exceptionHandling) ->
				exceptionHandling.accessDeniedHandler(logDenied))
				;
				return http.build();
				}
}


