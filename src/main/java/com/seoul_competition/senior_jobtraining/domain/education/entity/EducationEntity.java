package com.seoul_competition.senior_jobtraining.domain.education.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "education")
public class EducationEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;
  @Column(name = "state", nullable = false)
  private String state;
  @Column(name = "url", nullable = false)
  private String url;
  @Column(name = "price", nullable = false)
  private int price;
  @Column(name = "register_start", nullable = false)
  private LocalDateTime registerStart;
  @Column(name = "register_end", nullable = false)
  private LocalDateTime registerEnd;
  @Column(name = "education_start", nullable = false)
  private LocalDateTime educationStart;
  @Column(name = "education_end", nullable = false)
  private LocalDateTime educationEnd;

  @Builder
  public EducationEntity(String name, String state, String url, int price,
      LocalDateTime registerStart, LocalDateTime registerEnd, LocalDateTime educationStart,
      LocalDateTime educationEnd) {
    this.name = name;
    this.state = state;
    this.url = url;
    this.price = price;
    this.registerStart = registerStart;
    this.registerEnd = registerEnd;
    this.educationStart = educationStart;
    this.educationEnd = educationEnd;
  }
}
