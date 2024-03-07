package com.hendi.banktransfersystem.infrastructure.config.web.security.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hendi.banktransfersystem.entity.usertoken.exception.UserTokenNotFoundException;
import com.hendi.banktransfersystem.entity.usertoken.exception.UserTokenRevokedException;
import com.hendi.banktransfersystem.infrastructure.config.web.security.util.JwtUtils;
import com.hendi.banktransfersystem.usecase.usertoken.GetUserTokenUseCase;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RevokedJwtTokenFilter extends OncePerRequestFilter {

    private final GetUserTokenUseCase getUserTokenUseCase;
    private final JwtUtils jwtUtils;

    public RevokedJwtTokenFilter(GetUserTokenUseCase getUserTokenUseCase, JwtUtils jwtUtils) {
        this.getUserTokenUseCase = getUserTokenUseCase;
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String jwtToken = jwtUtils.parseJwt(request);

        if (jwtToken != null) {
            try {
                getUserTokenUseCase.execute(jwtToken);
            } catch (UserTokenNotFoundException ex) {
                throw new UserTokenRevokedException();
            }
        }

        filterChain.doFilter(request, response);
    }

}
