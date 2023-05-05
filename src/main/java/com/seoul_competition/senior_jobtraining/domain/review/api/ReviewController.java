package com.seoul_competition.senior_jobtraining.domain.review.api;

import com.seoul_competition.senior_jobtraining.domain.review.application.ReviewService;
import com.seoul_competition.senior_jobtraining.domain.review.dto.request.ReviewSaveReqDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/review")
public class ReviewController {

  private final ReviewService reviewService;

  @PostMapping
  public ResponseEntity<Void> createReview(@RequestBody @Valid ReviewSaveReqDto reqDto) {
    reviewService.create(reqDto);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

}
