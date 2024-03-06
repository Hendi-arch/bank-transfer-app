package com.hendi.banktransfersystem.infrastructure.config.web.security.handler;

import org.springframework.stereotype.Service;

import com.hendi.banktransfersystem.infrastructure.config.web.response.WebHttpErrorResponse;
import com.hendi.banktransfersystem.infrastructure.config.web.response.WebHttpResponse;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

@Service
public class MyAuthenticationHandler implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException ex)
			throws IOException, ServletException {
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

		List<WebHttpErrorResponse> messages = List.of(new WebHttpErrorResponse(null, ex.getMessage()));
		response.getWriter().write(WebHttpResponse.unauthorized(messages).toJson());
	}

}
