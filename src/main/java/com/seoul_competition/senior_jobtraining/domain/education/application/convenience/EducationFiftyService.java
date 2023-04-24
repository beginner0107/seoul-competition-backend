package com.seoul_competition.senior_jobtraining.domain.education.application.convenience;

import com.seoul_competition.senior_jobtraining.domain.education.dao.EducationRepository;
import com.seoul_competition.senior_jobtraining.domain.education.entity.Education;
import com.seoul_competition.senior_jobtraining.global.external.openApi.education.FiftyApi;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EducationFiftyService {

  private final EducationRepository educationRepository;
  private final FiftyApi fiftyApi;

  @Transactional
  public void saveFifty(JSONArray infoArr) {
    for (int i = 0; i < infoArr.size(); i++) {
      JSONObject jsonObject = (JSONObject) infoArr.get(i);
      Education education = Education.builder()
          .name((String) jsonObject.get("LCT_NM"))
          .state((String) jsonObject.get("LCT_STAT"))
          .url((String) jsonObject.get("CR_URL"))
          .price(Integer.parseInt((String) jsonObject.get("LCT_COST")))
          .capacity(Integer.parseInt((String) jsonObject.get("CR_PPL_STAT")))
          .registerStart((String) jsonObject.get("REG_STDE"))
          .registerEnd((String) jsonObject.get("REG_EDDE"))
          .educationStart((String) jsonObject.get("CR_STDE"))
          .educationEnd((String) jsonObject.get("CR_EDDE"))
          .build();
      educationRepository.save(education);
    }
  }

}
