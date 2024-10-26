package com.challenge.forohub.service;

import com.challenge.forohub.persistence.entity.User;

public interface JwtService {

  String extractUsername(String token);

  String generateToken(User user);

  Boolean isTokenValid(String token, User user);
}
