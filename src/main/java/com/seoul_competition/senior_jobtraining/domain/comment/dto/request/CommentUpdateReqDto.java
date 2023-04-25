package com.seoul_competition.senior_jobtraining.domain.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CommentUpdateReqDto(
    @NotNull(message = "게시글 번호를 입력해주세요.") Long postId,
    @NotBlank(message = "비밀번호를 입력해주세요.") String password,
    @NotBlank(message = "내용이 비어있습니다.") String content) {

}
