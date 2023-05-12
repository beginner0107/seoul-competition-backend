package com.seoul_competition.senior_jobtraining.domain.education.dto.request;

public record OriginIdRequest(
    Long originId
) {

  public static OriginIdRequest of(Long originId) {
    return new OriginIdRequest(originId);
  }
}
