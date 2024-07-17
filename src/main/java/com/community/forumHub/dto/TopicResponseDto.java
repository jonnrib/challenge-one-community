package com.community.forumHub.dto;

import com.community.forumHub.domain.Topic;

import java.time.LocalDateTime;

public record TopicResponseDto(
        Long id,
        String title,
        String message,
        LocalDateTime creationDate,
        String status,
        String courseName,
        String authorName) {

    public TopicResponseDto(Topic topic) {
        this(topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getCreationDate(),
                topic.getStatus(),
                topic.getCourse().getName(),
                topic.getAuthor().getName());
    }
}
