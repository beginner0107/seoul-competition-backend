package com.seoul_competition.senior_jobtraining.domain.education.dao;

import com.seoul_competition.senior_jobtraining.domain.education.entity.Education;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EducationRepository extends JpaRepository<Education, Long> {

  @Query("SELECT e FROM Education e WHERE e.name LIKE %:name% ORDER BY e.state DESC")
  List<Education> fs(@Param("name") String name);
}
