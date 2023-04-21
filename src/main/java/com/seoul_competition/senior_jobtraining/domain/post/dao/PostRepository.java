package com.seoul_competition.senior_jobtraining.domain.post.dao;

import com.seoul_competition.senior_jobtraining.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
