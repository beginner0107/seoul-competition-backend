package com.seoul_competition.senior_jobtraining.domain.post.application;

import com.seoul_competition.senior_jobtraining.domain.post.dao.PostRepository;
import com.seoul_competition.senior_jobtraining.domain.post.dto.request.PostSaveReqDto;
import com.seoul_competition.senior_jobtraining.domain.post.dto.request.PostUpdateReqDto;
import com.seoul_competition.senior_jobtraining.domain.post.dto.response.PostDetailResDto;
import com.seoul_competition.senior_jobtraining.domain.post.dto.response.PostResDto;
import com.seoul_competition.senior_jobtraining.domain.post.entity.Post;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

  private final PostRepository postRepository;

  @Transactional
  public Long savePost(PostSaveReqDto reqDto) {
    Post postPS = postRepository.save(reqDto.toEntity());
    return postPS.getId();
  }

  @Transactional
  public PostDetailResDto getPost(Long postId) {
    Post post = postRepository.findById(postId).orElseThrow(EntityNotFoundException::new);

    post.addHits();

    return PostDetailResDto.of(post.getId(), post.getNickname(), post.getTitle(), post.getContent(),
        post.getCreatedAt(), post.getHits(), post.getComments());
  }

  @Transactional
  public void update(Long postId, PostUpdateReqDto reqDto) {
    Post post = postRepository.findById(postId).orElseThrow(EntityNotFoundException::new);
    post.checkPassword(reqDto.password());

    post.update(reqDto.title(), reqDto.content());
  }

  @Transactional
  public void delete(Long postId, String password) {
    Post post = postRepository.findById(postId).orElseThrow(EntityNotFoundException::new);
    post.checkPassword(password);
    postRepository.delete(post);
  }

  public List<PostResDto> getPosts(int page, int size) {
    List<Post> posts = postRepository.findAllWithComments(PageRequest.of(page, size));
    return posts.stream()
        .map(PostResDto::of)
        .collect(Collectors.toList());
  }
}
