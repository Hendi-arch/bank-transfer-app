package com.hendi.banktransfersystem.infrastructure.config.web.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hendi.banktransfersystem.infrastructure.config.web.security.filter.CorsSecurityFilter;
import com.hendi.banktransfersystem.infrastructure.config.web.security.filter.SecurityMethodFilter;
import com.hendi.banktransfersystem.infrastructure.config.web.security.handler.MyAccessDeniedHandler;
import com.hendi.banktransfersystem.infrastructure.config.web.security.handler.MyAuthenticationHandler;
import com.hendi.banktransfersystem.infrastructure.config.web.security.service.MyUserDetailService;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class AppSecurityConfigurer {

	// Define final fields and inject them through constructor
	private final MyUserDetailService myUserDetailService;
	private final SecurityMethodFilter securityMethodFilter;
	private final CorsSecurityFilter corsSecurityFilter;
	private final MyAuthenticationHandler myAuthenticationHandler;
	private final MyAccessDeniedHandler myAccessDeniedHandler;

	private static final String[] UNSECURED_ENDPOINTS = {
			"/users/login",
			"/users/user",
			"/actuator/**"
	};

	public AppSecurityConfigurer(MyUserDetailService myUserDetailService,
			SecurityMethodFilter securityMethodFilter,
			CorsSecurityFilter corsSecurityFilter,
			MyAuthenticationHandler myAuthenticationHandler,
			MyAccessDeniedHandler myAccessDeniedHandler) {
		this.myUserDetailService = myUserDetailService;
		this.securityMethodFilter = securityMethodFilter;
		this.corsSecurityFilter = corsSecurityFilter;
		this.myAuthenticationHandler = myAuthenticationHandler;
		this.myAccessDeniedHandler = myAccessDeniedHandler;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		/**
		 * Disable CSRF protection since we use token-based authentication.
		 * 
		 * @see https://www.baeldung.com/spring-security-csrf#:~:text=If%20our%20stateless%20API%20uses%20token%2Dbased%20authentication%2C%20such%20as%20JWT%2C%20we%20don%27t%20need%20CSRF%20protection%2C%20and%20we%20must%20disable%20it%20as%20we%20saw%20earlier.
		 */
		http.csrf(AbstractHttpConfigurer::disable)
				.exceptionHandling(
						customizer -> customizer
								.accessDeniedHandler(myAccessDeniedHandler)
								.authenticationEntryPoint(myAuthenticationHandler))
				.sessionManagement(customizer -> customizer
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(
						customizer -> customizer
								.requestMatchers(UNSECURED_ENDPOINTS).permitAll()
								.anyRequest().authenticated());

		// Enable CORS
		http.cors(withDefaults());

		// Set user details service and add filters
		http.userDetailsService(myUserDetailService);
		http.addFilterBefore(corsSecurityFilter, ChannelProcessingFilter.class);
		http.addFilterBefore(securityMethodFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

}
