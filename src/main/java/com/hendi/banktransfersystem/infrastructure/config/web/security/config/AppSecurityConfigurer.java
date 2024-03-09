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

import com.hendi.banktransfersystem.infrastructure.config.db.schema.UserRoleSchema.RoleEnum;
import com.hendi.banktransfersystem.infrastructure.config.web.security.filter.CorsSecurityFilter;
import com.hendi.banktransfersystem.infrastructure.config.web.security.filter.RevokedJwtTokenFilter;
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
	private final RevokedJwtTokenFilter revokedJwtTokenFilter;
	private final MyAuthenticationHandler myAuthenticationHandler;
	private final MyAccessDeniedHandler myAccessDeniedHandler;

	private static final String[] PUBLIC_ENDPOINTS = { "/users/login", "/users/user" };
	private static final String[] SUPER_ADMIN_ENDPOINTS = { "/actuator/**" };
	private static final String[] ADMIN_ENDPOINTS = { "/usertokens/**", "/userroles/**" };
	private static final String[] USER_ENDPOINTS = { "/users/**", "/transactions/**" };

	public AppSecurityConfigurer(MyUserDetailService myUserDetailService,
			SecurityMethodFilter securityMethodFilter,
			CorsSecurityFilter corsSecurityFilter,
			RevokedJwtTokenFilter revokedJwtTokenFilter,
			MyAuthenticationHandler myAuthenticationHandler,
			MyAccessDeniedHandler myAccessDeniedHandler) {
		this.myUserDetailService = myUserDetailService;
		this.securityMethodFilter = securityMethodFilter;
		this.corsSecurityFilter = corsSecurityFilter;
		this.revokedJwtTokenFilter = revokedJwtTokenFilter;
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
								.requestMatchers(PUBLIC_ENDPOINTS).permitAll()
								.requestMatchers(SUPER_ADMIN_ENDPOINTS).hasRole(RoleEnum.SUPER_ADMIN.name())
								.requestMatchers(ADMIN_ENDPOINTS).hasAnyRole(
										RoleEnum.ADMIN.name(),
										RoleEnum.SUPER_ADMIN.name())
								.requestMatchers(USER_ENDPOINTS).hasAnyRole(
										RoleEnum.USER.name(),
										RoleEnum.ADMIN.name(),
										RoleEnum.SUPER_ADMIN.name()))
				.cors(withDefaults());

		http.userDetailsService(myUserDetailService)
				.addFilterBefore(corsSecurityFilter, ChannelProcessingFilter.class)
				.addFilterBefore(securityMethodFilter, UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(revokedJwtTokenFilter, SecurityMethodFilter.class);

		return http.build();
	}

}
