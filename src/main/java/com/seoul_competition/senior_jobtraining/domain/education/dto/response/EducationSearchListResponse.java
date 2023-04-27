package com.seoul_competition.senior_jobtraining.domain.education.dto.response;

import java.util.List;

public record EducationSearchListResponse(List<EducationResponse> data,
                                          int totalCount) {

}
