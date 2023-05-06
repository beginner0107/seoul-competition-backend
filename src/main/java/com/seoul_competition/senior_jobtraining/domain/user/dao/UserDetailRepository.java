package com.seoul_competition.senior_jobtraining.domain.user.dao;

import com.seoul_competition.senior_jobtraining.domain.user.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {

}
