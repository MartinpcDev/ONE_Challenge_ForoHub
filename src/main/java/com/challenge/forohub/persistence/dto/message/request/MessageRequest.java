package com.challenge.forohub.persistence.dto.message.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MessageRequest(
    String content,
    @JsonProperty("topic_id")
    Long topicId
) {

}
