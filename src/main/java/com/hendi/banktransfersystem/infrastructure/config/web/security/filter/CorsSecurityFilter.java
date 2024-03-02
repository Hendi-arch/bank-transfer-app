package com.hendi.banktransfersystem.infrastructure.config.web.security.filter;

import java.io.IOException;
import java.util.Arrays;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class CorsSecurityFilter extends OncePerRequestFilter {

	// Allowed origins
	private static final String[] ALLOWED_ORIGINS = {};

	// Allowed HTTP methods
	private static final String[] ALLOWED_METHODS = { "GET", "POST", "PUT", "OPTIONS" };

	// Allowed HTTP headers
	private static final String ALLOWED_HEADERS = "authorization, content-type, xsrf-token, user-agent";

	// Exposed HTTP headers
	private static final String EXPOSED_HEADERS = "xsrf-token";

	// Max age of preflight requests in seconds
	private static final int MAX_AGE_SECONDS = 3600;

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
			@NonNull FilterChain filterChain)
			throws ServletException, IOException {
		// Add the CORS headers to the response
		addCorsHeaders(request, response);

		// Handle the preflight requests (OPTIONS) by setting the response status to OK
		// and returning
		if ("OPTIONS".equals(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			// Forward the request to the next filter in the chain
			filterChain.doFilter(request, response);
		}
	}

	// Adds the necessary CORS headers to the response
	private void addCorsHeaders(HttpServletRequest request, HttpServletResponse response) {
		String origin = request.getHeader("Origin");
		if (Arrays.asList(ALLOWED_ORIGINS).contains(origin)) {
			response.setHeader("Access-Control-Allow-Origin", origin);
		}
		response.setHeader("Access-Control-Allow-Methods", String.join(", ", ALLOWED_METHODS));
		response.setHeader("Access-Control-Max-Age", String.valueOf(MAX_AGE_SECONDS));
		response.setHeader("Access-Control-Allow-Headers", ALLOWED_HEADERS);
		response.addHeader("Access-Control-Expose-Headers", EXPOSED_HEADERS);
	}
}
