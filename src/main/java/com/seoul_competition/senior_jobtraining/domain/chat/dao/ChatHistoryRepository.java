package com.seoul_competition.senior_jobtraining.domain.chat.dao;

import com.seoul_competition.senior_jobtraining.domain.chat.entity.ChatHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatHistoryRepository extends JpaRepository<ChatHistory, Long> {

}
