package com.example.Config;

import com.example.Intersepter.AuthInterceptor;
import com.example.UnauthorizedException;
import com.example.dto.TokenDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

public class TokenInterSepter implements HandlerInterceptor {
    @Autowired
    private AuthInterceptor authInterceptor;

    private TokenDTO tokenDTO;

    public TokenInterSepter(AuthInterceptor authorizationIntegration, TokenDTO tokenDTO) {
        super();
        this.authInterceptor = authorizationIntegration;
        this.tokenDTO = tokenDTO;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (handleRequestsWithoutToken(request) || "options".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        if (request.getHeader("authorization") != null
                && !request.getHeader("authorization").isEmpty()) {
            boolean result = authInterceptor.validateToken(request.getHeader("authorization"));
            if (result) {
                tokenDTO.setAccessToken(request.getHeader("authorization"));
                return result;
            } else {
                throw new UnauthorizedException("Invalid token");
            }
        } else {
            throw new UnauthorizedException("Invalid token");
        }
    }

    private boolean handleRequestsWithoutToken(HttpServletRequest request) {
        try {
            if (request.getRequestURL().toString().endsWith("/")
                    || request.getRequestURL().toString().contains("/api/user/login")) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
