package com.seoul_competition.senior_jobtraining.domain.post.dao;

import com.seoul_competition.senior_jobtraining.domain.post.entity.Post;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {

  @Query("SELECT p FROM Post p LEFT JOIN FETCH p.comments WHERE p.id = :id")
  Optional<Post> findByIdWithComments(@Param("id") Long id);

  @Query(value = "SELECT p FROM Post p LEFT JOIN FETCH p.comments",
      countQuery = "SELECT count(p) FROM Post p")
  List<Post> findAllWithComments(Pageable pageable);
}
