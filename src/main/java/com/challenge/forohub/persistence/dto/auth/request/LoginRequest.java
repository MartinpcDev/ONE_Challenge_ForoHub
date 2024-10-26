package com.challenge.forohub.persistence.dto.auth.request;

public record LoginRequest(
    String username,
    String password
) {

}
