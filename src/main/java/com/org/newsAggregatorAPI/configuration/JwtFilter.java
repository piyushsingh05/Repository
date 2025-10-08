package com.org.newsAggregatorAPI.configuration;

import com.org.newsAggregatorAPI.entity.User;
import com.org.newsAggregatorAPI.service.NewsAggregatorService;
import com.org.newsAggregatorAPI.util.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configurers.SecurityContextConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.Security;
import java.util.ArrayList;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private NewsAggregatorService newsAggregatorService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authenticationRequest = request.getHeader("Authorization");
        if(request.getRequestURI().contains("api/login")||
         request.getRequestURI().contains("api/register")||
         request.getRequestURI().contains("/h2-console"))
        {
            filterChain.doFilter(request, response);
            return;
        }
        if(authenticationRequest == null || !authenticationRequest.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Missing or invalid Authorization header");
            return;
        }

        String token = authenticationRequest.substring(7);
        try{
            String userName = jwtUtil.getUsernameFromToken(token);
            if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null){
                User  userDB = newsAggregatorService.findByUsername(userName);
                if(userDB !=null){
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDB,null ,new ArrayList<>());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }

            }
        }  catch (Exception e) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("Invalid JWT token");
        return;
    }

        filterChain.doFilter(request,response);
    }
}
