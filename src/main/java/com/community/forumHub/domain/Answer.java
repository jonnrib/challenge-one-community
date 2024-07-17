package com.community.forumHub.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;

    @ManyToOne
    private Topic topic;

    private LocalDateTime creationDate;

    @ManyToOne
    private User author;
    private Boolean solution;
}
