package com.community.forumHub.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity(name = "Topic")
@NoArgsConstructor
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    private LocalDateTime creationDate;
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    private Course course;

    @ManyToOne(fetch = FetchType.EAGER)
    private User author;

    @OneToMany(mappedBy = "topic")
    private List<Answer> answers;

    public Topic(String title, String message, Course course, User author){
        this.title = title;
        this.message = message;
        this.creationDate = LocalDateTime.now();
        this.status = "Not Resolved";
        this.course = course;
        this.author = author;
    }
}
