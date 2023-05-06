package com.seoul_competition.senior_jobtraining.domain.review.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReviewUpdateReqDto(
    @NotBlank(message = "비밀번호를 입력해주세요.") String password,
    @NotBlank(message = "내용이 비어있습니다.") String content
    ) {

}
