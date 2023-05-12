package com.seoul_competition.senior_jobtraining.domain.education.dao;

import com.seoul_competition.senior_jobtraining.domain.education.dto.response.RecommendationEducationsDto;
import com.seoul_competition.senior_jobtraining.domain.education.entity.Education;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface EducationRepository extends JpaRepository<Education, Long>,
    QuerydslPredicateExecutor<Education> {

  Page<Education> findByNameContainingOrderByStatusDesc(Pageable pageable, String name);

  Optional<Education> findByOriginId(Long originId);

  @Query("SELECT new com.seoul_competition.senior_jobtraining.domain.education.dto.response"
      + ".RecommendationEducationsDto(e.id, e.name, e.status, e.capacity, e.registerStart"
      + ", e.registerEnd, e.educationStart, e.educationEnd, e.url, e.hits) "
      + "FROM Education e WHERE e.id IN :ids")
  List<RecommendationEducationsDto> findByIdIn(List<Long> ids);

}
