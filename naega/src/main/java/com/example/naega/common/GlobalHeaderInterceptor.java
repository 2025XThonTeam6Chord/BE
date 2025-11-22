package com.example.naega.common;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class GlobalHeaderInterceptor implements HandlerInterceptor {
    public static final ThreadLocal<String> localThread = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String userId = request.getHeader("X-USER-ID");

        if (userId == null) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), "Missing X-USER-ID");
            return false;
        }

        localThread.set(userId);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        GlobalHeaderInterceptor.localThread.remove();
    }
}
