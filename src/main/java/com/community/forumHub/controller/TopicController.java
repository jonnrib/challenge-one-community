package com.community.forumHub.controller;

import com.community.forumHub.dto.TopicRegistrationDto;
import com.community.forumHub.service.TopicService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topics")
@SecurityRequirement(name = "bearer-key")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @PostMapping
    @Transactional
    public ResponseEntity<?> save(@RequestHeader("Authorization") String token,
                                  @RequestBody @Valid TopicRegistrationDto data,
                                  UriComponentsBuilder uriBuilder) {

        var topicDetails = topicService.save(data, token);
        var uri = uriBuilder.path("/topics/{id}").buildAndExpand(topicDetails.id()).toUri();

        return ResponseEntity.created(uri).body(topicDetails);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable Long id){
        var topicDetails = topicService.find(id);
        return ResponseEntity.ok(topicDetails);
    }

    @GetMapping
    public ResponseEntity<?> list(){
        var topicsDetails = topicService.list();
        return ResponseEntity.ok(topicsDetails);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> update(@RequestHeader("Authorization") String token,
                                    @PathVariable Long id,
                                    @RequestBody @Valid TopicRegistrationDto data){
        var topicDetails = topicService.update(id, data, token);

        return ResponseEntity.ok(topicDetails);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@RequestHeader("Authorization") String token,
                                    @PathVariable Long id){
        topicService.delete(id, token);
        return ResponseEntity.noContent().build();
    }
}
