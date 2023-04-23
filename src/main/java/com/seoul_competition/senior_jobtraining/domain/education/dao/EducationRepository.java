package com.seoul_competition.senior_jobtraining.domain.education.dao;

import com.seoul_competition.senior_jobtraining.domain.education.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationRepository extends JpaRepository<Education, Long> {

}
