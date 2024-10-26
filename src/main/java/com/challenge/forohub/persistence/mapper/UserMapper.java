package com.challenge.forohub.persistence.mapper;

import com.challenge.forohub.persistence.dto.auth.request.RegisterRequest;
import com.challenge.forohub.persistence.dto.user.request.UpdateUserRequest;
import com.challenge.forohub.persistence.dto.user.response.UserResponse;
import com.challenge.forohub.persistence.entity.Role;
import com.challenge.forohub.persistence.entity.User;
import java.util.List;

public class UserMapper {

  public static UserResponse toUserDto(User user) {
    if (user == null) {
      return null;
    }

    return new UserResponse(
        user.getId(),
        user.getUsername()
    );
  }

  public static List<UserResponse> toUserDtoList(List<User> users) {
    if (users == null) {
      return null;
    }

    return users.stream()
        .map(UserMapper::toUserDto)
        .toList();
  }

  public static User toUserEntity(RegisterRequest userDto) {
    if (userDto == null) {
      return null;
    }

    User user = new User();
    user.setName(userDto.name());
    user.setUsername(userDto.username());
    user.setPassword(userDto.password());
    user.setRole(Role.USER);

    return user;
  }

  public static void updateUserEntity(User oldUser, UpdateUserRequest userDto) {
    if (oldUser != null && userDto != null) {
      if (userDto.name() != null) {
        oldUser.setName(userDto.name());
      }
      if (userDto.role() != null) {
        oldUser.setRole(userDto.role());
      }
      if (userDto.password() != null) {
        oldUser.setPassword(userDto.password());
      }
    }
  }
}
