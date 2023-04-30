package com.seoul_competition.senior_jobtraining.domain.education.application;

import com.seoul_competition.senior_jobtraining.domain.education.application.convenience.EducationFiftyService;
import com.seoul_competition.senior_jobtraining.domain.education.application.convenience.EducationSeniorService;
import com.seoul_competition.senior_jobtraining.domain.education.dao.EducationRepository;
import com.seoul_competition.senior_jobtraining.domain.education.dto.response.EducationListPageResponse;
import com.seoul_competition.senior_jobtraining.domain.education.dto.response.EducationResponse;
import com.seoul_competition.senior_jobtraining.domain.education.entity.Education;
import com.seoul_competition.senior_jobtraining.global.error.ErrorCode;
import com.seoul_competition.senior_jobtraining.global.error.exception.BusinessException;
import com.seoul_competition.senior_jobtraining.global.external.openApi.education.SeniorApi;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

  public EducationListPageResponse findAllByPage(Pageable pageable) {
    Page<Education> educationPage = educationRepository.findAll(pageable);

    checkPageNumber(pageable, educationPage);

    return new EducationListPageResponse(entityToResponse(educationPage),
        educationPage.getTotalPages() - 1, pageable.getPageNumber());
  }

  public EducationListPageResponse findAllByName(Pageable pageable, String name) {
    Page<Education> educationPage = educationRepository.findByNameContainingOrderByStateDesc(
        pageable, name);

    checkPageNumber(pageable, educationPage);

    return new EducationListPageResponse(entityToResponse(educationPage),
        educationPage.getTotalPages() - 1, pageable.getPageNumber());
  }

  private void checkPageNumber(Pageable pageable, Page<Education> educationPage) {
    if (pageable.getPageNumber() >= educationPage.getTotalPages()) {
      throw new BusinessException(ErrorCode.PAGE_NOT_FOUND);
    }
  }

  private List<EducationResponse> entityToResponse(Page<Education> educationPage) {
    return educationPage.getContent().stream().map(EducationResponse::new)
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
