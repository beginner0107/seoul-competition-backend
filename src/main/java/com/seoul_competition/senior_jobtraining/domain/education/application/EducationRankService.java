package com.seoul_competition.senior_jobtraining.domain.education.application;

import com.seoul_competition.senior_jobtraining.domain.education.dao.EducationRepository;
import com.seoul_competition.senior_jobtraining.domain.education.dto.response.EducationListPageResponse;
import com.seoul_competition.senior_jobtraining.domain.education.dto.response.EducationRankResDto;
import com.seoul_competition.senior_jobtraining.domain.education.dto.response.EducationResponse;
import com.seoul_competition.senior_jobtraining.domain.education.entity.Education;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EducationRankService {

  private final EducationRepository educationRepository;

  public EducationRankResDto getFiveByHits(boolean user) {

    List<Education> educationList = educationRepository.findAll(
            PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "hits")))
        .getContent();

    List<EducationResponse> educationResponseList = educationList.stream()
        .map(EducationResponse::new)
        .collect(Collectors.toList());

    return new EducationRankResDto(educationResponseList, user);
  }
}
