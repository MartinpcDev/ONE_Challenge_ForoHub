package com.challenge.forohub.service.impl;

import com.challenge.forohub.exceptions.InvalidAuthException;
import com.challenge.forohub.persistence.dto.auth.request.LoginRequest;
import com.challenge.forohub.persistence.dto.auth.request.RegisterRequest;
import com.challenge.forohub.persistence.dto.auth.response.AuthResponse;
import com.challenge.forohub.persistence.dto.auth.response.RegisterResponse;
import com.challenge.forohub.persistence.entity.User;
import com.challenge.forohub.persistence.mapper.UserMapper;
import com.challenge.forohub.persistence.repository.UserRepository;
import com.challenge.forohub.service.IAuthService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements IAuthService {

  private final UserRepository userRepository;
  private final JwtServiceImpl jwtService;
  private final AuthenticationManager authenticationManager;
  private final BCryptPasswordEncoder passwordEncoder;

  @Autowired
  public AuthServiceImpl(UserRepository userRepository, JwtServiceImpl jwtService,
      AuthenticationManager authenticationManager, BCryptPasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.jwtService = jwtService;
    this.authenticationManager = authenticationManager;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public AuthResponse login(LoginRequest request) {
    Optional<User> user = userRepository.findByUsernameIgnoreCase(request.username());

    if (user.isEmpty()) {
      throw new InvalidAuthException("Credenciales Invalidas");
    }

    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.username(), request.password())
    );
    return new AuthResponse("Logueado Correctamente", jwtService.generateToken(user.get()));
  }

  @Override
  public RegisterResponse register(RegisterRequest request) {
    Optional<User> user = userRepository.findByUsernameIgnoreCase(request.username());
    if (user.isPresent()) {
      throw new InvalidAuthException("El usuario ya esta en uso");
    }

    User createUSer = UserMapper.toUserEntity(new RegisterRequest(request.name(),
        request.username(), passwordEncoder.encode(request.password())));
    userRepository.save(createUSer);

    return new RegisterResponse("Registrado correctamente");
  }

}
