package com.seoul_competition.senior_jobtraining.domain.education.api;

import static org.assertj.core.api.Assertions.*;

import com.seoul_competition.senior_jobtraining.domain.education.dto.response.EducationListResponse;
import com.seoul_competition.senior_jobtraining.global.external.openApi.education.FiftyApi;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class EducationControllerTest {

  @Autowired
  EducationController educationController;

  @Autowired
  FiftyApi fiftyApi;

  @Test
  @DisplayName("모든 교육을 조회할 때, http가 정상적으로 200을 반환했는지 테스트")
  public void should_httpStatusOk_when_getAllEducations() {
    assertThat(educationController.getAllEducations().getStatusCode()).isEqualTo(
        HttpStatus.OK);

  }

  @Test
  @DisplayName("모든 교육을 조회할 때, http Body에 들어가는지 테스트")
  public void should_saveResponseBody_when_getAllEducations() {
    assertThat(
        educationController.getAllEducations().getBody().toString().length() > 1000).isTrue();
  }

}