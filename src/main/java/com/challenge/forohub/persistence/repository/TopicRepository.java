package com.challenge.forohub.persistence.repository;

import com.challenge.forohub.persistence.entity.Topic;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {

  List<Topic> findAllByUserId(Long userId);
}
