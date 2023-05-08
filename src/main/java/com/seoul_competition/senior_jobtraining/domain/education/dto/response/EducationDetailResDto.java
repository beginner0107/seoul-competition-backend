package com.seoul_competition.senior_jobtraining.domain.education.dto.response;

import com.seoul_competition.senior_jobtraining.domain.education.entity.Education;
import com.seoul_competition.senior_jobtraining.domain.review.dto.response.ReviewResDto;
import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

public record EducationDetailResDto(
    Long id,
    String name,
    String status,

    String price,
    Integer capacity,
    String registerStart,
    String registerEnd,
    String educationStart,
    String educationEnd,
    String url,
    Long hits,
    List<ReviewResDto> reviews) {

  public EducationDetailResDto(Education education) {
    this(education.getId(), education.getName(), education.getStatus(),
        new DecimalFormat("###,###").format(education.getPrice()),
        education.getCapacity(), education.getRegisterStart(), education.getRegisterEnd(),
        education.getEducationStart(), education.getEducationEnd(), education.getUrl(),
        education.getHits(),
        education.getReviews().stream().map(ReviewResDto::new).collect(Collectors.toList()));
  }
}
