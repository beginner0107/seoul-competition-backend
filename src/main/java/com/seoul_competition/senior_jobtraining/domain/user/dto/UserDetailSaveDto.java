package com.seoul_competition.senior_jobtraining.domain.user.dto;

import com.seoul_competition.senior_jobtraining.domain.user.entity.BoardCategory;
import com.seoul_competition.senior_jobtraining.domain.user.entity.UserDetail;
import io.jsonwebtoken.Claims;

public record UserDetailSaveDto(
    BoardCategory category,
    String gender,
    String age,
    String location,
    String interest,
    Long postId
) {

  public static UserDetailSaveDto from(Claims claims, Long postId, BoardCategory boardCategory) {
    return new UserDetailSaveDto(
        boardCategory,
        claims.get("gender").toString(),
        claims.get("age").toString(),
        claims.get("location").toString(),
        claims.get("interest").toString(),
        postId
    );
  }

  public static UserDetail toEntity(UserDetailSaveDto saveDto) {
    return UserDetail.builder()
        .boardCategory(saveDto.category)
        .gender(saveDto.gender)
        .age(saveDto.age)
        .location(saveDto.location)
        .interest(saveDto.interest)
        .postId(saveDto.postId)
        .build();
  }
}
