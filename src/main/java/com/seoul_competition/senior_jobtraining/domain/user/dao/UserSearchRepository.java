package com.seoul_competition.senior_jobtraining.domain.user.dao;

import com.seoul_competition.senior_jobtraining.domain.user.entity.UserSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSearchRepository extends JpaRepository<UserSearch, Long> {

}
