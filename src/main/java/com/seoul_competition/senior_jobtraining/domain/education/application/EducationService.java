package com.seoul_competition.senior_jobtraining.domain.education.application;

import static com.seoul_competition.senior_jobtraining.domain.education.entity.QEducation.education;

import com.querydsl.core.BooleanBuilder;
import com.seoul_competition.senior_jobtraining.domain.education.application.convenience.EducationFiftyService;
import com.seoul_competition.senior_jobtraining.domain.education.application.convenience.EducationSeniorService;
import com.seoul_competition.senior_jobtraining.domain.education.dao.EducationRepository;
import com.seoul_competition.senior_jobtraining.domain.education.dto.request.EducationSearchReqDto;
import com.seoul_competition.senior_jobtraining.domain.education.dto.response.EducationDetailResDto;
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

  private int seniorSize=0;
  private int fiftySize=0;

  public EducationListPageResponse getEducations(Pageable pageable, EducationSearchReqDto reqDto) {

    BooleanBuilder builder = new BooleanBuilder();

    if (reqDto.name() != null) {
      builder.and(education.name.contains(reqDto.name()));
    }
    if (reqDto.status() != null) {
      builder.and(education.status.eq(reqDto.status()));
    }
    if (reqDto.minPrice() != null) {
      builder.and(education.price.goe(reqDto.minPrice()));
    }
    if (reqDto.maxPrice() != null) {
      builder.and(education.price.loe(reqDto.maxPrice()));
    }
    if (reqDto.startDate() != null) {
      builder.and(education.educationStart.goe(reqDto.startDate()));
    }
    if (reqDto.endDate() != null) {
      builder.and(education.educationEnd.loe(reqDto.endDate()));
    }

    Page<Education> educationPage = educationRepository.findAll(builder, pageable);

    checkPageNumber(pageable, educationPage);

    return new EducationListPageResponse(entityToResponse(educationPage),
        educationPage.getTotalPages() - 1, pageable.getPageNumber(),
        educationPage.getTotalElements());
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

  @Transactional
  public EducationDetailResDto findById(Long id) {
    Education findEducation = educationRepository.findById(id).get();
    findEducation.hitsPlus();
    return new EducationDetailResDto(findEducation);
  }


  @Transactional
  public void saveAll() {
    educationFiftyService.saveFifty();
    educationSeniorService.saveSenior(0);
    seniorSize = educationSeniorService.getTotalCount();
  }

}
