package com.sportseventapplication.auth.service;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MyJwtFilter extends OncePerRequestFilter{
	
	
	@Autowired
	private JWTService jwtService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String header = request.getHeader(HttpHeaders.AUTHORIZATION);
		
		if(header!=null) {
			
			String[] authElements = header.split(" ");
			
			if(authElements.length ==2 && "Bearer".equals(authElements[0])) {
				try {
				SecurityContextHolder.getContext().setAuthentication(jwtService.getAuthentication(authElements[1]));
				}
				catch (Exception e) {
					
					SecurityContextHolder.clearContext();
				}
				
			}
			
		}
		filterChain.doFilter(request, response);
		
		
	}
	
	

}
 