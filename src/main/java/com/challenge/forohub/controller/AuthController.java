package com.challenge.forohub.controller;

import com.challenge.forohub.persistence.dto.auth.request.LoginRequest;
import com.challenge.forohub.persistence.dto.auth.request.RegisterRequest;
import com.challenge.forohub.persistence.dto.auth.response.AuthResponse;
import com.challenge.forohub.persistence.dto.auth.response.RegisterResponse;
import com.challenge.forohub.persistence.entity.User;
import com.challenge.forohub.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final AuthService authService;

  @Autowired
  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/login")
  public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest request) {
    return ResponseEntity.ok(authService.login(request));
  }

  @PostMapping("/register")
  public ResponseEntity<RegisterResponse> register(@RequestBody @Valid RegisterRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(request));
  }

  @GetMapping("/profile")
  @PreAuthorize("isAuthenticated()")
  public void profile(@AuthenticationPrincipal UserDetails userDetails) {
    Long id = ((User) userDetails).getId();
    System.out.println(authService.profile(id));
  }
}
