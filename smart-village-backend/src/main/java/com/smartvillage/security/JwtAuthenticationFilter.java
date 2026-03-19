package com.smartvillage.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	    @Autowired
	    private JwtUtil jwtUtil;

	    @Autowired
	    private CustomUserDetailsService userDetailsService;

	    @Override
	    protected void doFilterInternal(HttpServletRequest request,
	                                    HttpServletResponse response,
	                                    FilterChain filterChain)
	            throws ServletException, IOException {

	        final String authHeader = request.getHeader("Authorization");
	        String email = null;
	        String token = null;

	        // 1️ Check if header exists and starts with Bearer
	        if (authHeader != null && authHeader.startsWith("Bearer ")) {
	            token = authHeader.substring(7);
	            email = jwtUtil.extractEmail(token);
	        }

	        // 2️ If email found and user not already authenticated
	        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

	            UserDetails userDetails = userDetailsService.loadUserByUsername(email);

	            try {
		            // validate token
	            if (jwtUtil.validateToken(token, userDetails)) {

	                UsernamePasswordAuthenticationToken authToken =
	                        new UsernamePasswordAuthenticationToken(
	                                userDetails,
	                                null,
	                                userDetails.getAuthorities()
	                        );

	                authToken.setDetails(
	                        new WebAuthenticationDetailsSource().buildDetails(request)
	                );

	                // 4️ Set authentication in Spring Security
	                SecurityContextHolder.getContext().setAuthentication(authToken);
	            }
	        }
	       
	         catch (ExpiredJwtException e) {
	            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	            response.getWriter().write("Token expired");
	            return;
	        } catch (Exception e) {
	            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	            response.getWriter().write("Invalid token");
	            return;
	        }
	        }

	        // Continue filter chain
	        filterChain.doFilter(request, response);
	    
	    }

}
