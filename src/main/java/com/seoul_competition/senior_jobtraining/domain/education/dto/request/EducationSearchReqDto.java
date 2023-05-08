package com.seoul_competition.senior_jobtraining.domain.education.dto.request;

public record EducationSearchReqDto(
    String name,
    String status,
    String startDate,
    String endDate,
    Integer minPrice,
    Integer maxPrice) {

}
