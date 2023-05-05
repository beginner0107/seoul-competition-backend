package com.seoul_competition.senior_jobtraining.domain.review.application;

import com.seoul_competition.senior_jobtraining.domain.education.dao.EducationRepository;
import com.seoul_competition.senior_jobtraining.domain.education.entity.Education;
import com.seoul_competition.senior_jobtraining.domain.review.dao.ReviewRepository;
import com.seoul_competition.senior_jobtraining.domain.review.dto.request.ReviewSaveReqDto;
import com.seoul_competition.senior_jobtraining.domain.review.dto.request.ReviewUpdateReqDto;
import com.seoul_competition.senior_jobtraining.domain.review.entity.Review;
import com.seoul_competition.senior_jobtraining.global.error.ErrorCode;
import com.seoul_competition.senior_jobtraining.global.error.exception.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Getter
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {

  private final ReviewRepository reviewRepository;
  private final EducationRepository educationRepository;

  @Transactional
  public void create(ReviewSaveReqDto reqDto) {
    Education education = educationRepository.findById(reqDto.EducationId())
        .orElseThrow(() -> new EntityNotFoundException(ErrorCode.EDUCATION_NOT_EXISTS));

    reviewRepository.save(reqDto.toEntity(education));
  }

  @Transactional
  public void update(Long reviewId, ReviewUpdateReqDto reqDto) {
    educationRepository.findById(reqDto.EducationId())
        .orElseThrow(() -> new EntityNotFoundException(ErrorCode.EDUCATION_NOT_EXISTS));

    Review review = reviewRepository.findById(reviewId)
        .orElseThrow(() -> new EntityNotFoundException(ErrorCode.REVIEW_NOT_EXISTS));

    review.checkPassword(reqDto.password());

    review.update(reqDto.content());
  }

  public void delete(Long reviewId, String password) {
    Review review = reviewRepository.findById(reviewId)
        .orElseThrow(() -> new EntityNotFoundException(ErrorCode.REVIEW_NOT_EXISTS));
    System.out.println(password);
    review.checkPassword(password);
    reviewRepository.delete(review);

  }
}
