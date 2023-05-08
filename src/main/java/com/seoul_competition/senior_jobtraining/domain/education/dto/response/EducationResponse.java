package com.seoul_competition.senior_jobtraining.domain.education.dto.response;

import com.seoul_competition.senior_jobtraining.domain.education.entity.Education;
import java.text.DecimalFormat;
import lombok.Getter;


@Getter
public class EducationResponse {

  private Long id;
  private String name;
  private String status;

  private String price;
  private Integer capacity;
  private String registerStart;
  private String registerEnd;
  private String educationStart;
  private String educationEnd;
  private String url;
  private Long hits;
  private int reviewsCount;

  public EducationResponse(Education education) {
    this.id = education.getId();
    this.name = education.getName();
    this.status = education.getStatus();
    this.url = education.getUrl();
    this.price = new DecimalFormat("###,###").format(education.getPrice());
    this.capacity = education.getCapacity();
    this.registerStart = education.getRegisterStart();
    this.registerEnd = education.getRegisterEnd();
    this.educationStart = education.getEducationStart();
    this.educationEnd = education.getEducationEnd();
    this.hits = education.getHits();
    this.reviewsCount = education.getReviews().size();
  }

}
