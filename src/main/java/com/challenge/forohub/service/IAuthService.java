package com.challenge.forohub.service;

import com.challenge.forohub.persistence.dto.auth.request.LoginRequest;
import com.challenge.forohub.persistence.dto.auth.request.RegisterRequest;
import com.challenge.forohub.persistence.dto.auth.response.AuthResponse;
import com.challenge.forohub.persistence.dto.auth.response.RegisterResponse;

public interface IAuthService {

  AuthResponse login(LoginRequest request);

  RegisterResponse register(RegisterRequest request);
}
