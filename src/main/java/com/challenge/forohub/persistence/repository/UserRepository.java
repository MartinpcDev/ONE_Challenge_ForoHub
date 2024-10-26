package com.challenge.forohub.persistence.repository;

import com.challenge.forohub.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
