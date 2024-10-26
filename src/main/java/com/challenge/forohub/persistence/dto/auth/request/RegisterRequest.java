package com.challenge.forohub.persistence.dto.auth.request;

public record RegisterRequest(
    String name,
    String username,
    String password
) {

}
