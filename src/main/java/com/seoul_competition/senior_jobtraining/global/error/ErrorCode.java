package com.seoul_competition.senior_jobtraining.global.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

  POST_NOT_EXISTS("해당 게시글은 존재하지 않습니다.", HttpStatus.BAD_REQUEST),
  PASSWORD_MISMATCH("비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);

  private String message;
  private HttpStatus httpStatus;

  ErrorCode(String message, HttpStatus httpStatus) {
    this.message = message;
    this.httpStatus = httpStatus;
  }
}
