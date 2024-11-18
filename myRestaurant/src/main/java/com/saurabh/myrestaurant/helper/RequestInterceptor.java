package com.saurabh.myrestaurant.helper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class RequestInterceptor implements HandlerInterceptor {
    private final JWThelper jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // got the token
            String userName = jwtUtil.extractUserName(token);

            if(userName == null || !jwtUtil.validateToken(token, userName)) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }

        }
        else{
            //response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); //this will only set 401
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"http header not correct"); //this will also send error message
            return false;
        }
        return true;
    }
}
