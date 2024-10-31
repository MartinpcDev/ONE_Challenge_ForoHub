package com.challenge.forohub.service.impl;

import com.challenge.forohub.exceptions.TopicNotFoundException;
import com.challenge.forohub.exceptions.UserNotFoundException;
import com.challenge.forohub.persistence.dto.topico.request.TopicRequest;
import com.challenge.forohub.persistence.dto.topico.response.TopicResponse;
import com.challenge.forohub.persistence.entity.Topic;
import com.challenge.forohub.persistence.entity.User;
import com.challenge.forohub.persistence.mapper.TopicMapper;
import com.challenge.forohub.persistence.repository.TopicRepository;
import com.challenge.forohub.persistence.repository.UserRepository;
import com.challenge.forohub.service.ITopicService;
import com.challenge.forohub.utils.DeleteResponse;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicServiceImpl implements ITopicService {

  private final TopicRepository topicRepository;
  private final UserRepository userRepository;

  @Autowired
  public TopicServiceImpl(TopicRepository topicRepository, UserRepository userRepository) {
    this.topicRepository = topicRepository;
    this.userRepository = userRepository;
  }

  @Override
  public TopicResponse createTopic(Long userId, TopicRequest request) {
    User userExists = userRepository.findById(userId)
        .orElseThrow(() -> new UserNotFoundException("User not found"));
    Topic topic = TopicMapper.toTopicEntity(request);
    topic.setUser(userExists);
    return TopicMapper.toTopicDto(topicRepository.save(topic));
  }

  @Override
  public TopicResponse getTopicById(Long topicId) {
    return TopicMapper.toTopicDto(topicRepository.findById(topicId)
        .orElseThrow(() -> new TopicNotFoundException("No hay topicos para este usuario")));
  }

  @Override
  public List<TopicResponse> getAllTopicsByUser(Long userId) {
    List<Topic> topics = topicRepository.findAllByUserId(userId);
    return TopicMapper.toTopicDtoList(topics);
  }

  @Override
  public List<TopicResponse> getAllTopics() {
    return TopicMapper.toTopicDtoList(topicRepository.findAll());
  }

  @Override
  @Transactional
  public TopicResponse updateTopic(Long userId, Long topicId, TopicRequest request) {
    User userExists = userRepository.findById(userId)
        .orElseThrow(() -> new UserNotFoundException("User not Found"));
    Topic topicExists = topicRepository.findById(topicId)
        .orElseThrow(() -> new TopicNotFoundException("Topic not found"));
    if (!Objects.equals(userExists.getId(), topicExists.getUser().getId())) {
      throw new IllegalArgumentException("Este Topic pertenece a otro usuario");
    }
    TopicMapper.updateTopicEntity(topicExists, request);
    return TopicMapper.toTopicDto(topicRepository.save(topicExists));
  }

  @Override
  @Transactional
  public DeleteResponse deleteTopic(Long topicId, Long userId) {
    User userExists = userRepository.findById(userId)
        .orElseThrow(() -> new UserNotFoundException("User not Found"));
    Topic topicExists = topicRepository.findById(topicId)
        .orElseThrow(() -> new TopicNotFoundException("Topic not found"));
    if (!Objects.equals(userExists.getId(), topicExists.getUser().getId())) {
      throw new IllegalArgumentException("Este Topic pertenece a otro usuario");
    }
    topicRepository.delete(topicExists);
    return new DeleteResponse("Topico eliminado Correctamente");
  }
}
