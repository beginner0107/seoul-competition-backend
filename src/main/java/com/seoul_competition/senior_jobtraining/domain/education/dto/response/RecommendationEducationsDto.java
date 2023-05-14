package com.seoul_competition.senior_jobtraining.domain.education.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RecommendationEducationsDto {

  private Long id;
  private String name;
  private String status;
  private int price;
  private Integer capacity;
  @JsonFormat(pattern = "yyyy.MM.dd")
  private LocalDate registerStart;
  @JsonFormat(pattern = "yyyy.MM.dd")
  private LocalDate registerEnd;
  @JsonFormat(pattern = "yyyy.MM.dd")
  private LocalDate educationStart;
  @JsonFormat(pattern = "yyyy.MM.dd")
  private LocalDate educationEnd;
  private String url;
  private Long hits;

}
