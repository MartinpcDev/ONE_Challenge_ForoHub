package com.challenge.forohub.persistence.dto.user.request;

import com.challenge.forohub.persistence.entity.Role;

public record UpdateUserRequest(
    String name,
    Role role,
    String password
) {

}
