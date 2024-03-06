package com.hendi.banktransfersystem.infrastructure.config.web.security.handler;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.hendi.banktransfersystem.infrastructure.config.web.response.WebHttpErrorResponse;
import com.hendi.banktransfersystem.infrastructure.config.web.response.WebHttpResponse;

@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException ex)
			throws IOException, ServletException {
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);

		List<WebHttpErrorResponse> messages = List.of(new WebHttpErrorResponse(null, ex.getMessage()));
		response.getWriter().write(WebHttpResponse.forbidden(messages).toJson());
	}

}
