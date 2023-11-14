package com.neidev.tivia.security.config;

import com.neidev.tivia.domain.repository.BeneficiarioRepository;
import com.neidev.tivia.security.service.JwtTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class FiltroSecConfig extends OncePerRequestFilter {

    private final JwtTokenService tokenService;
    private final BeneficiarioRepository beneficiarioRepository;

    public FiltroSecConfig(JwtTokenService tokenService, BeneficiarioRepository beneficiarioRepository) {
        this.tokenService = tokenService;
        this.beneficiarioRepository = beneficiarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        var token = recuperarToken(request);
        if (token != null) {
            var subject = tokenService.verificarToken(token);
            UserDetails user = beneficiarioRepository.findByLogin(subject);

            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    String recuperarToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;

        return authHeader.replace("Bearer ", "");
    }
}
