package com.seoul_competition.senior_jobtraining.domain.education.application.convenience;

import com.seoul_competition.senior_jobtraining.domain.education.dao.EducationRepository;
import com.seoul_competition.senior_jobtraining.domain.education.entity.Education;
import com.seoul_competition.senior_jobtraining.global.error.BusinessException;
import com.seoul_competition.senior_jobtraining.global.external.openApi.education.FiftyApi;
import java.text.DecimalFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Getter
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EducationFiftyService {

  private final EducationRepository educationRepository;
  private final FiftyApi fiftyApi;
  private static int startPage = 0;
  private static int endPage = 999;
  private static int increaseUnit = 1000;

  @Transactional
  public void saveFifty() {
    try {
      while (true) {
        fiftyApi.getJson(startPage, endPage);
        saveInfoArr(fiftyApi.getInfoArr());
        startPage += increaseUnit;
        endPage += increaseUnit;
      }
    } catch (BusinessException e) { // 끝났다는 뜻
    }
  }

  private void saveInfoArr(JSONArray infoArr) {
    for (int i = 0; i < infoArr.size(); i++) {
      JSONObject jsonObject = (JSONObject) infoArr.get(i);
      Education education = Education.builder()
          .name((String) jsonObject.get("LCT_NM"))
          .status((String) jsonObject.get("LCT_STAT"))
          .url((String) jsonObject.get("CR_URL"))
          .price(Integer.parseInt((String) jsonObject.get("LCT_COST")))
          .capacity(Integer.parseInt(
              (String) jsonObject.get("CR_PPL_STAT") != "" ? (String) jsonObject.get(
                  "CR_PPL_STAT") : "0"))
          .registerStart((String) jsonObject.get("REG_STDE"))
          .registerEnd((String) jsonObject.get("REG_EDDE"))
          .educationStart((String) jsonObject.get("CR_STDE"))
          .educationEnd((String) jsonObject.get("CR_EDDE"))
          .hits(0L)
          .originId(Integer.parseInt((String) jsonObject.get("LCT_NO")))
          .build();
      educationRepository.save(education);
    }
  }
}


