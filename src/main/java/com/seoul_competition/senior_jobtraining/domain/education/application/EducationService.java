package com.seoul_competition.senior_jobtraining.domain.education.application;

import com.seoul_competition.senior_jobtraining.domain.education.dao.EducationRepository;
import com.seoul_competition.senior_jobtraining.domain.education.dto.response.EducationListResponse;
import com.seoul_competition.senior_jobtraining.domain.education.dto.response.EducationResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EducationService {

  private final EducationRepository educationRepository;

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
}
