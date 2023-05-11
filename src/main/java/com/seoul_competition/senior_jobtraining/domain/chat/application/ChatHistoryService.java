package com.seoul_competition.senior_jobtraining.domain.chat.application;

import com.seoul_competition.senior_jobtraining.domain.chat.dao.ChatHistoryRepository;
import com.seoul_competition.senior_jobtraining.domain.chat.dto.ChatSaveDto;
import com.seoul_competition.senior_jobtraining.domain.chat.dto.request.ChatFeedbackReqDto;
import com.seoul_competition.senior_jobtraining.domain.chat.entity.ChatHistory;
import com.seoul_competition.senior_jobtraining.global.error.ErrorCode;
import com.seoul_competition.senior_jobtraining.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatHistoryService {

  private final ChatHistoryRepository chatHistoryRepository;

  public Long createAChat(ChatSaveDto dto) {
    ChatHistory chat = chatHistoryRepository.save(dto.toEntity());
    return chat.getId();
  }

  public void updateAChat(ChatFeedbackReqDto reqDto) {
    ChatHistory chatHistory = chatHistoryRepository.findById(reqDto.id())
        .orElseThrow(() -> new EntityNotFoundException(ErrorCode.CHAT_NOT_EXISTS));
    chatHistory.update(reqDto.feedback());
  }
}
