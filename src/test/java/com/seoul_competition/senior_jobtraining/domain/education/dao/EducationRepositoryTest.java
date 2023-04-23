package com.seoul_competition.senior_jobtraining.domain.education.dao;

import static org.assertj.core.api.Assertions.assertThat;

import com.seoul_competition.senior_jobtraining.domain.education.entity.Education;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EducationRepositoryTest {

  @Autowired
  EducationRepository educationRepository;

  @BeforeEach
  public void beforeEach() {
    Education Education1 = Education.builder()
        .name("aaa")
        .state("as")
        .url("Asda")
        .price(120)
        .registerStart(LocalDateTime.now())
        .registerEnd(LocalDateTime.now())
        .educationStart(LocalDateTime.now())
        .educationEnd(LocalDateTime.now())
        .build();

    Education Education2 = Education.builder()
        .name("bbb")
        .state("saz")
        .url("http:Asda")
        .price(12011)
        .registerStart(LocalDateTime.now())
        .registerEnd(LocalDateTime.now())
        .educationStart(LocalDateTime.now())
        .educationEnd(LocalDateTime.now())
        .build();

    educationRepository.save(Education1);
    educationRepository.save(Education2);
  }

  @DisplayName("Education 모두 DB에 정상 저장되는지 확인")
  @Test
  public void when_findAll_should_right() {
    List<Education> findEducations = educationRepository.findAll();
    assertThat(findEducations.size()).isEqualTo(2);
  }

}