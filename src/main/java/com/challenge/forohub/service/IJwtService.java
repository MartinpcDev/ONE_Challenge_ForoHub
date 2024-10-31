package com.challenge.forohub.service;

import com.challenge.forohub.persistence.entity.User;

public interface IJwtService {

  String extractUsername(String token);

  String generateToken(User user);

  Boolean isTokenValid(String token, User user);
}
