package com.challenge.forohub.service;

import com.challenge.forohub.persistence.dto.topico.request.TopicRequest;
import com.challenge.forohub.persistence.dto.topico.response.TopicResponse;
import com.challenge.forohub.utils.DeleteResponse;
import java.util.List;

public interface ITopicService {

  TopicResponse createTopic(Long userId, TopicRequest request);

  TopicResponse getTopicById(Long topicId);

  List<TopicResponse> getAllTopicsByUser(Long userId);

  List<TopicResponse> getAllTopics();

  TopicResponse updateTopic(Long userId, Long topicId, TopicRequest request);

  DeleteResponse deleteTopic(Long topicId, Long userId);
}
