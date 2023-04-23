package com.seoul_competition.senior_jobtraining.domain.post.dto.response;

import com.seoul_competition.senior_jobtraining.domain.post.entity.Post;

public record PostResDto(Long id,
                         String nickname,
                         String title,
                         String content,
                         Long hits,
                         Long commentCount) {

  public static PostResDto of(Post post) {
    return new PostResDto(
        post.getId(),
        post.getNickname(),
        post.getTitle(),
        post.getContent(),
        post.getHits(),
        (long) post.getComments().size()
    );
  }

}
