package com.seoul_competition.senior_jobtraining.domain.education.application;

import com.seoul_competition.senior_jobtraining.domain.education.application.convenience.EducationFiftyService;
import com.seoul_competition.senior_jobtraining.domain.education.application.convenience.EducationSeniorService;
import com.seoul_competition.senior_jobtraining.domain.education.dao.EducationRepository;
import com.seoul_competition.senior_jobtraining.domain.education.dto.response.EducationListResponse;
import com.seoul_competition.senior_jobtraining.domain.education.dto.response.EducationResponse;
import com.seoul_competition.senior_jobtraining.global.external.openApi.education.SeniorApi;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Getter
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EducationService {

  private final EducationRepository educationRepository;
  private final SeniorApi seniorApi;
  private final EducationSeniorService educationSeniorService;
  private final EducationFiftyService educationFiftyService;

  public EducationListResponse findAll() {
    return new EducationListResponse(entityToResponse());
  }

  private List<EducationResponse> entityToResponse() {
    return educationRepository.findAll().stream()
        .map(EducationResponse::new)
        .collect(Collectors.toList());
  }

  public EducationResponse findById(Long id) {
    return new EducationResponse(educationRepository.findById(id).get());
  }

  @Transactional
  public void saveAll() {
    educationFiftyService.saveFifty();
    educationSeniorService.saveSenior(seniorApi.getInfoArr());
  }

}
