package com.seoul_competition.senior_jobtraining.domain.comment.application;

import com.seoul_competition.senior_jobtraining.domain.comment.dao.CommentRepository;
import com.seoul_competition.senior_jobtraining.domain.comment.dto.request.CommentSaveReqDto;
import com.seoul_competition.senior_jobtraining.domain.comment.dto.request.CommentUpdateReqDto;
import com.seoul_competition.senior_jobtraining.domain.comment.entity.Comment;
import com.seoul_competition.senior_jobtraining.domain.post.dao.PostRepository;
import com.seoul_competition.senior_jobtraining.domain.post.entity.Post;
import com.seoul_competition.senior_jobtraining.global.error.ErrorCode;
import com.seoul_competition.senior_jobtraining.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

  private final PostRepository postRepository;
  private final CommentRepository commentRepository;

  @Transactional
  public void create(CommentSaveReqDto reqDto) {
    Post post = postRepository.findById(reqDto.postId())
        .orElseThrow(() -> new EntityNotFoundException(ErrorCode.POST_NOT_EXISTS));
    commentRepository.save(reqDto.toEntity(post));
  }

  @Transactional
  public void update(Long commentId, CommentUpdateReqDto reqDto) {
    postRepository.findById(reqDto.postId())
        .orElseThrow(() -> new EntityNotFoundException(ErrorCode.POST_NOT_EXISTS));

    Comment comment = commentRepository.findById(commentId)
        .orElseThrow(() -> new EntityNotFoundException(
            ErrorCode.COMMENT_NOT_EXISTS));
    comment.checkPassword(reqDto.password());

    comment.update(reqDto.content());
  }

  @Transactional
  public void delete(Long commentId, String password) {
    Comment comment = commentRepository.findById(commentId)
        .orElseThrow(() -> new EntityNotFoundException(ErrorCode.COMMENT_NOT_EXISTS));
    comment.checkPassword(password);
    commentRepository.delete(comment);
  }
}
