package com.seoul_competition.senior_jobtraining.global.error;

import static lombok.AccessLevel.PRIVATE;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = PRIVATE)
public class ErrorResponse {

  public static ErrorResponse from(String message) {
    return new ErrorResponse(message);
  }

  private final String message;
}
