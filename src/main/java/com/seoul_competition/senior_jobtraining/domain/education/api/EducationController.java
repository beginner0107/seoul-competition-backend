package com.seoul_competition.senior_jobtraining.domain.education.api;

import com.seoul_competition.senior_jobtraining.domain.education.application.EducationService;
import com.seoul_competition.senior_jobtraining.domain.education.dto.response.EducationListPageResponse;
import com.seoul_competition.senior_jobtraining.domain.education.dto.response.EducationResponse;
import com.seoul_competition.senior_jobtraining.domain.education.dto.response.EducationSearchListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/educations")
@RequiredArgsConstructor
public class EducationController {

  private final EducationService educationService;

  private boolean first = true;

  @GetMapping
  public ResponseEntity<EducationListPageResponse> getAllEducations(
      @PageableDefault(sort = "state", size = 20, direction = Direction.DESC) Pageable pageable) {
    if (first) {
      educationService.saveAll();
      first = false;
    }
    EducationListPageResponse response = educationService.findAllByPage(pageable);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @GetMapping("/search")
  public ResponseEntity<EducationSearchListResponse> getSearchEducations(
      @RequestParam("name") String name) {
    EducationSearchListResponse response = educationService.findAllByName(name);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @GetMapping("/{educationId}")
  public ResponseEntity<EducationResponse> getEducation(@PathVariable long educationId) {
    EducationResponse response = educationService.findById(educationId);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }
}
