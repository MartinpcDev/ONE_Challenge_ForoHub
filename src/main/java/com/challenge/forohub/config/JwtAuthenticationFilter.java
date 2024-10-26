package com.challenge.forohub.config;

import com.challenge.forohub.exceptions.InvalidAuthException;
import com.challenge.forohub.persistence.entity.User;
import com.challenge.forohub.persistence.repository.UserRepository;
import com.challenge.forohub.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import lombok.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtService jwtService;
  private final UserRepository userRepository;
  private final UserDetailsService userDetailsService;

  public JwtAuthenticationFilter(JwtService jwtService, UserRepository userRepository,
      UserDetailsService userDetailsService) {
    this.jwtService = jwtService;
    this.userRepository = userRepository;
    this.userDetailsService = userDetailsService;
  }

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain) throws ServletException, IOException {
    if (request.getServletPath().startsWith("/api/v1/auth")) {
      filterChain.doFilter(request, response);
    }
    String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      String token = authHeader.substring(7);
      String username = jwtService.extractUsername(token);
      Optional<User> userExist = userRepository.findByUsernameIgnoreCase(username);
      if (userExist.isPresent()) {
        final boolean isTokenValid = jwtService.isTokenValid(token, userExist.get());
        if (isTokenValid) {
          final UserDetails userDetails = this.userDetailsService.loadUserByUsername(token);
          UsernamePasswordAuthenticationToken authtoken = new UsernamePasswordAuthenticationToken(
              userDetails,
              null,
              userDetails.getAuthorities()
          );
          authtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          SecurityContextHolder.getContext().setAuthentication(authtoken);
        } else {
          throw new InvalidAuthException("Token invalid");
        }
      } else {
        throw new InvalidAuthException("Token invalid");
      }
    }

    filterChain.doFilter(request, response);
  }
}
