package com.seoul_competition.senior_jobtraining.domain.education.api;

import com.seoul_competition.senior_jobtraining.domain.education.application.EducationService;
import com.seoul_competition.senior_jobtraining.domain.education.dto.request.EducationSearchReqDto;
import com.seoul_competition.senior_jobtraining.domain.education.dto.response.EducationDetailResDto;
import com.seoul_competition.senior_jobtraining.domain.education.dto.response.EducationListPageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/educations")
@RequiredArgsConstructor
public class EducationController {

  private final EducationService educationService;

  private boolean first = true;

  @GetMapping
  public ResponseEntity<EducationListPageResponse> getAllEducations(
      @PageableDefault(sort = "status", size = 20, direction = Direction.DESC) Pageable pageable,
      @ModelAttribute EducationSearchReqDto reqDto) {
    if (first) {
      educationService.saveAll();
      first = false;
    } else {
      educationService.update();
    }
    EducationListPageResponse response = educationService.getEducations(pageable, reqDto);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @GetMapping("/{educationId}")
  public ResponseEntity<EducationDetailResDto> getEducation(@PathVariable long educationId) {
    EducationDetailResDto response = educationService.findById(educationId);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }
}
