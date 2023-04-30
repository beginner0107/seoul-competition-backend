package com.seoul_competition.senior_jobtraining.domain.education.dto.response;

import com.seoul_competition.senior_jobtraining.domain.education.entity.Education;
import lombok.Getter;


@Getter
public class EducationResponse {

  private Long id;
  private String name;
  private String state;
  private int price;
  private int capacity;
  private String registerStart;
  private String registerEnd;
  private String educationStart;
  private String educationEnd;
  private String url;
  private Long views;

  public EducationResponse(Education education) {
    this.id = education.getId();
    this.name = education.getName();
    this.state = education.getState();
    this.url = education.getUrl();
    this.price = education.getPrice();
    this.capacity = education.getCapacity();
    this.registerStart = education.getRegisterStart();
    this.registerEnd = education.getRegisterEnd();
    this.educationStart = education.getEducationStart();
    this.educationEnd = education.getEducationEnd();
    this.views = education.getViews();
  }

}
