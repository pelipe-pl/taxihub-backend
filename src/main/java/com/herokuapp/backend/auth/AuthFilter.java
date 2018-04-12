package com.herokuapp.backend.auth;

import com.herokuapp.backend.client.ClientRepository;
import com.herokuapp.backend.corporation.CorporationRepository;
import com.herokuapp.backend.driver.DriverRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter extends OncePerRequestFilter {

    private final String TOKEN_HEADER = "authorization";
    private final AuthenticationManager manager;

    public AuthFilter(AuthenticationManager manager) {
        this.manager = manager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String tokenAuth = request.getHeader(TOKEN_HEADER);
        if (tokenAuth == null) {
            filterChain.doFilter(request, response);
            return;
        }
        Authentication auth = manager.authenticate(new UsernamePasswordAuthenticationToken(null, tokenAuth));
        SecurityContextHolder.getContext().setAuthentication(auth);
        filterChain.doFilter(request, response);
    }
}
