package com.community.forumHub.repository;

import com.community.forumHub.domain.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    boolean existsByTitle(String title);
    boolean existsByMessage(String message);

    List<Topic> findAllByOrderByCreationDateDesc();
}
