package com.seoul_competition.senior_jobtraining.domain.post.api;

import com.seoul_competition.senior_jobtraining.domain.post.application.PostService;
import com.seoul_competition.senior_jobtraining.domain.post.dto.request.PostSaveReqDto;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

  private final PostService postService;

  @PostMapping
  public ResponseEntity<Void> savePost(@RequestBody @Valid PostSaveReqDto reqDto) {
    Long postId = postService.savePost(reqDto);
    return ResponseEntity.status(HttpStatus.CREATED)
        .location(URI.create("/posts/" + postId))
        .build();
  }
}
