package com.seoul_competition.senior_jobtraining.domain.education.dto.response;

import com.seoul_competition.senior_jobtraining.domain.education.entity.Education;
import lombok.Getter;


@Getter
public class EducationResponse {

  private String name;
  private String state;
  private String url;
  private int price;
  private String registerStart;
  private String registerEnd;
  private String educationStart;
  private String educationEnd;

  public EducationResponse(Education education) {
    this.name = education.getName();
    this.state = education.getState();
    this.url = education.getUrl();
    this.price = education.getPrice();
    this.registerStart = education.getRegisterStart();
    this.registerEnd = education.getRegisterEnd();
    this.educationStart = education.getEducationStart();
    this.educationEnd = education.getEducationEnd();
  }

}
