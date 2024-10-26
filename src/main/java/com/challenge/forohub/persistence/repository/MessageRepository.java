package com.challenge.forohub.persistence.repository;

import com.challenge.forohub.persistence.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
