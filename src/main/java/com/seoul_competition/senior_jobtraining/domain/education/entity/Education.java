package com.seoul_competition.senior_jobtraining.domain.education.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.lang.String;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "education")
public class Education {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "price", nullable = false)
  private int price;
  @Column(name = "capacity", nullable = false)
  private int capacity;
  @Column(name = "name", nullable = false)
  private String name;
  @Column(name = "state", nullable = false)
  private String state;
  @Column(name = "url", nullable = false)
  private String url;
  @Column(name = "register_start", nullable = false)
  private String registerStart;
  @Column(name = "register_end", nullable = false)
  private String registerEnd;
  @Column(name = "education_start", nullable = false)
  private String educationStart;
  @Column(name = "education_end", nullable = false)
  private String educationEnd;

  @Column(name = "vews", nullable = false)
  private Long views;

  @Builder
  public Education(String name, String state, String url, int price, int capacity,
      String registerStart, String registerEnd, String educationStart,
      String educationEnd, Long views) {

    this.name = name;
    this.state = state;
    this.url = url;
    this.price = price;
    this.capacity = capacity;
    this.registerStart = registerStart;
    this.registerEnd = registerEnd;
    this.educationStart = educationStart;
    this.educationEnd = educationEnd;
    this.views = views;
  }

  public void viewPlus() {
    this.views++;
  }
}
