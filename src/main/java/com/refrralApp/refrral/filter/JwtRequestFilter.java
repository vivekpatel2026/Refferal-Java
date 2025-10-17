package com.refrralApp.refrral.filter;

import com.refrralApp.refrral.utility.JwtUtility;
import org.hibernate.annotations.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtUtility jwtUtil;

    public JwtRequestFilter(JwtUtility jwtUtil) { this.jwtUtil = jwtUtil; }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Skip public endpoints
        if (request.getServletPath().startsWith("/api/auth")) {
            filterChain.doFilter(request, response);
            return;
        }


        String header = request.getHeader("Authorization");


        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            if (!jwtUtil.validateToken(token)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid or expired token");
                return;
            }
            String detail=jwtUtil.getEmailAndDetailFromToken(token);
            String[] str=detail.split(" ");
            System.out.println(Arrays.toString(str));
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(str[0], null, List.of());
            SecurityContextHolder.getContext().setAuthentication(authToken);
            request.setAttribute("email",str[0]);
            request.setAttribute("userId",str[1]);
        }

        filterChain.doFilter(request, response);


    }
}
