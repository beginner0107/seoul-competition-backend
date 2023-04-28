package com.seoul_competition.senior_jobtraining.domain.post.application;

import com.seoul_competition.senior_jobtraining.domain.post.dao.PostRepository;
import com.seoul_competition.senior_jobtraining.domain.post.dto.request.PostSaveReqDto;
import com.seoul_competition.senior_jobtraining.domain.post.dto.request.PostUpdateReqDto;
import com.seoul_competition.senior_jobtraining.domain.post.dto.response.PostDetailResDto;
import com.seoul_competition.senior_jobtraining.domain.post.dto.response.PostListResponse;
import com.seoul_competition.senior_jobtraining.domain.post.dto.response.PostResDto;
import com.seoul_competition.senior_jobtraining.domain.post.entity.Post;
import com.seoul_competition.senior_jobtraining.global.error.ErrorCode;
import com.seoul_competition.senior_jobtraining.global.error.exception.BusinessException;
import com.seoul_competition.senior_jobtraining.global.error.exception.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    Post post = postRepository.findById(postId).orElseThrow(
        () -> new EntityNotFoundException(
            ErrorCode.POST_NOT_EXISTS));

    post.addHits();

    return PostDetailResDto.of(post.getId(), post.getNickname(), post.getTitle(), post.getContent(),
        post.getCreatedAt(), post.getHits(), post.getComments());
  }

  @Transactional
  public void update(Long postId, PostUpdateReqDto reqDto) {
    Post post = postRepository.findById(postId).orElseThrow(
        () -> new EntityNotFoundException(
            ErrorCode.POST_NOT_EXISTS));
    post.checkPassword(reqDto.password());

    post.update(reqDto.title(), reqDto.content());
  }

  @Transactional
  public void delete(Long postId, String password) {
    Post post = postRepository.findById(postId).orElseThrow(
        () -> new EntityNotFoundException(
            ErrorCode.POST_NOT_EXISTS));
    post.checkPassword(password);
    postRepository.delete(post);
  }

  public PostListResponse getPosts(Pageable pageable, String searchTerm) {
    if (pageable.getPageNumber() <= 0) {
      throw new BusinessException(ErrorCode.PAGE_NOT_FOUND);
    }
    pageable = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize(),
        pageable.getSort());
    Page<Post> postPage;
    if (searchTerm != null && !searchTerm.isEmpty()) {
      postPage = postRepository.findByTitleOrContent(searchTerm, pageable);
    } else {
      postPage = postRepository.findAll(pageable);
    }
    List<PostResDto> posts = postPage.getContent().stream().map(PostResDto::of)
        .collect(Collectors.toList());
    return new PostListResponse(posts, postPage.getTotalPages(), postPage.getNumber() + 1);
  }


  public int getTotalPages(int size) {
    int totalCount = postRepository.getTotalCount();
    return (int) Math.ceil((double) totalCount / size);
  }

}
