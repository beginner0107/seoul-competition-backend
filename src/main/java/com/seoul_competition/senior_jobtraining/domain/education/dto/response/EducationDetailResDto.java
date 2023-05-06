package com.seoul_competition.senior_jobtraining.domain.education.dto.response;

import com.seoul_competition.senior_jobtraining.domain.education.entity.Education;
import com.seoul_competition.senior_jobtraining.domain.review.entity.Review;
import java.util.List;

public record EducationDetailResDto(
    Long id,
    String name,
    String state,

    String price,
    int capacity,
    String registerStart,
    String registerEnd,
    String educationStart,
    String educationEnd,
    String url,
    Long hits) {

  public EducationDetailResDto(Education education) {
    this(education.getId(), education.getName(), education.getState(), education.getPrice(),
        education.getCapacity(), education.getRegisterStart(), education.getRegisterEnd(),
        education.getEducationStart(), education.getEducationEnd(), education.getUrl(),
        education.getHits());
  }

}
