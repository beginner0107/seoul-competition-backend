package com.seoul_competition.senior_jobtraining.domain.user.dto;

import com.seoul_competition.senior_jobtraining.domain.user.entity.BoardCategory;
import com.seoul_competition.senior_jobtraining.domain.user.entity.UserSearch;
import io.jsonwebtoken.Claims;

public record UserSearchSaveDto(
    BoardCategory category,
    String gender,
    String age,
    String location,
    String interest,
    String keyword
) {

  public static UserSearch toEntity(UserSearchSaveDto saveDto) {
    return UserSearch.builder()
        .category(saveDto.category)
        .gender(saveDto.gender)
        .age(saveDto.age)
        .location(saveDto.location)
        .interest(saveDto.interest)
        .keyword(saveDto.keyword)
        .build();
  }

  public static UserSearchSaveDto from(Claims claims, String searchValue) {
    return new UserSearchSaveDto(
        BoardCategory.FREE,
        claims.get("gender").toString(),
        claims.get("age").toString(),
        claims.get("location").toString(),
        claims.get("interest").toString(),
        searchValue
    );
  }
}
