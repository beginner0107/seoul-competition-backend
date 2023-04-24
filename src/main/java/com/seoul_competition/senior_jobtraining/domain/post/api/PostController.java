package com.seoul_competition.senior_jobtraining.domain.post.api;

import com.seoul_competition.senior_jobtraining.domain.post.application.PostService;
import com.seoul_competition.senior_jobtraining.domain.post.dto.request.PostSaveReqDto;
import com.seoul_competition.senior_jobtraining.domain.post.dto.request.PostUpdateReqDto;
import com.seoul_competition.senior_jobtraining.domain.post.dto.response.PostDetailResDto;
import com.seoul_competition.senior_jobtraining.domain.post.dto.response.PostResDto;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {

  private final PostService postService;

  @PostMapping
  public ResponseEntity<Void> savePost(@RequestBody @Valid PostSaveReqDto reqDto) {
    Long postId = postService.savePost(reqDto);
    return ResponseEntity.status(HttpStatus.CREATED)
        .location(URI.create("/posts/" + postId))
        .build();
  }

  @GetMapping
  public ResponseEntity<List<PostResDto>> getPosts(@RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "20") int size) {
    List<PostResDto> posts = postService.getPosts(page - 1, size);
    return ResponseEntity.ok(posts);
  }


  @GetMapping("/{postId}")
  public ResponseEntity<PostDetailResDto> getPost(@PathVariable Long postId) {
    PostDetailResDto postDetailResDto = postService.getPost(postId);
    return ResponseEntity.status(HttpStatus.OK)
        .body(postDetailResDto);
  }

  @PutMapping("/{postId}")
  public ResponseEntity<Void> updatePost(@PathVariable Long postId,
      @RequestBody @Valid PostUpdateReqDto reqDto) {
    postService.update(postId, reqDto);
    return ResponseEntity.status(HttpStatus.CREATED)
        .location(URI.create("/posts/" + postId))
        .build();
  }

  @DeleteMapping("/{postId}")
  public ResponseEntity<Void> deletePost(@PathVariable Long postId,
      @RequestBody Map<String, String> password) {
    postService.delete(postId, password.get("password"));
    return ResponseEntity.noContent().build();
  }
}
