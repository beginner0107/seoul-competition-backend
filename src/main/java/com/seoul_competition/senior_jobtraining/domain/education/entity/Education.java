package com.seoul_competition.senior_jobtraining.domain.education.entity;

import com.seoul_competition.senior_jobtraining.domain.review.entity.Review;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.lang.String;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "education")
public class Education {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "price", nullable = false)
  private String price;
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

  @Column(name = "hits", nullable = false)
  private Long hits;

  @OneToMany(mappedBy = "education", cascade = CascadeType.REMOVE)
  private List<Review> reviews = new ArrayList<>();

  @CreatedDate
  @Column(nullable = false)
  private LocalDateTime createdAt;

  @Builder
  public Education(String name, String state, String url, String price, int capacity,
      String registerStart, String registerEnd, String educationStart,
      String educationEnd, Long hits) {

    this.name = name;
    this.state = state;
    this.url = url;
    this.price = price;
    this.capacity = capacity;
    this.registerStart = registerStart;
    this.registerEnd = registerEnd;
    this.educationStart = educationStart;
    this.educationEnd = educationEnd;
    this.hits = hits;
  }

  public void hitsPlus() {
    this.hits++;
  }
}
