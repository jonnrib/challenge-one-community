package com.community.forumHub.service;

import com.community.forumHub.domain.Course;
import com.community.forumHub.domain.Topic;
import com.community.forumHub.domain.User;
import com.community.forumHub.dto.TopicRegistrationDto;
import com.community.forumHub.dto.TopicResponseDto;
import com.community.forumHub.infra.exception.UnauthorizedUserException;
import com.community.forumHub.infra.exception.ValidationException;
import com.community.forumHub.infra.exception.security.JWTTokenService;
import com.community.forumHub.repository.CourseRepository;
import com.community.forumHub.repository.TopicRepository;
import com.community.forumHub.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTTokenService tokenService;

    @Transactional
    public TopicResponseDto save(TopicRegistrationDto data, String token) {
        validateTopicData(data);

        User author = getUserFromToken(token);
        Course course = courseRepository.getReferenceById(data.courseId());
        Topic topic = new Topic(data.title(), data.message(), course, author);

        topic = topicRepository.save(topic);

        return new TopicResponseDto(topic);
    }

    public TopicResponseDto find(Long id) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Topic not found"));
        return new TopicResponseDto(topic);
    }

    public List<TopicResponseDto> list() {
        List<Topic> topics = topicRepository.findAllByOrderByCreationDateDesc();
        return topics.stream()
                .map(TopicResponseDto::new)
                .toList();
    }

    @Transactional
    public TopicResponseDto update(Long id, TopicRegistrationDto data, String token) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Topic not found"));
        User author = getUserFromToken(token);

        if (!author.equals(topic.getAuthor())) {
            throw new UnauthorizedUserException("User without permission");
        }

        validateTopicData(data);

        topic.setTitle(data.title());
        topic.setMessage(data.message());
        Course course = courseRepository.getReferenceById(data.courseId());
        topic.setCourse(course);

        return new TopicResponseDto(topic);
    }

    @Transactional
    public void delete(Long id, String token) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Topic not found"));
        User author = getUserFromToken(token);

        if (!author.equals(topic.getAuthor())) {
            throw new UnauthorizedUserException("User without permission");
        }

        topicRepository.deleteById(id);
    }

    private void validateTopicData(TopicRegistrationDto data) {
        if (!courseRepository.existsById(data.courseId())) {
            throw new ValidationException("Course not found");
        }
        boolean hasSameTitle = topicRepository.existsByTitle(data.title());
        boolean hasSameMessage = topicRepository.existsByMessage(data.message());

        if (hasSameTitle || hasSameMessage) {
            throw new ValidationException("A topic with this title or message already exists");
        }
    }

    public User getUserFromToken(String token) {
        String email = tokenService.getSubject(token.replace("Bearer ", "").trim());
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found: " + email));
    }
}
