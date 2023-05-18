package com.seoul_competition.senior_jobtraining.domain.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDto(@NotBlank(message = "성별을 입력해주세요.") String gender,
                      @NotNull(message = "나이대를 입력해주세요.") int age,
                      @NotBlank(message = "위치를 입력해주세요.") String location,
                      @NotBlank(message = "관심사를 입력해주세요.") String interest) {

  public static UserDto of(String gender, int age, String location, String interest) {
    return new UserDto(gender, age, location, interest);
  }
}
