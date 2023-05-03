package com.seoul_competition.senior_jobtraining.domain.education.application.convenience;

import com.seoul_competition.senior_jobtraining.domain.education.dao.EducationRepository;
import com.seoul_competition.senior_jobtraining.domain.education.entity.Education;
import java.text.DecimalFormat;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EducationSeniorService {

  private final EducationRepository educationRepository;

  private final static String BEFORE_WAITING_CONTENT = "접수중";
  private final static String AFTER_WAITING_CONTENT = "수강신청중";
  private static DecimalFormat decimalFormat = new DecimalFormat("###,###");

  @Transactional
  public void saveSenior(JSONArray infoArr) {
    for (int i = 0; i < infoArr.size(); i++) {
      JSONObject jsonObject = (JSONObject) infoArr.get(i);

      String applyState = getCommonApplyState(jsonObject);

      Education education = Education.builder()
          .name((String) jsonObject.get("SUBJECT"))
          .state(applyState)
          .url((String) jsonObject.get("VIEWDETAIL"))
          .price(decimalFormat.format(Integer.parseInt((String) jsonObject.get("REGISTCOST"))))
          .capacity(Integer.parseInt((String) jsonObject.get("REGISTPEOPLE")))
          .registerStart(((String) jsonObject.get("APPLICATIONSTARTDATE")).replaceAll("-", "."))
          .registerEnd(((String) jsonObject.get("APPLICATIONENDDATE")).replaceAll("-", "."))
          .educationStart(((String) jsonObject.get("STARTDATE")).replaceAll("-", "."))
          .educationEnd(((String) jsonObject.get("ENDDATE")).replaceAll("-", "."))
          .hits(0L)
          .originId(Integer.parseInt((String) jsonObject.get("IDX")))
          .build();
      educationRepository.save(education);
    }
  }

  private static String getCommonApplyState(JSONObject jsonObject) {
    String applyState = (String) jsonObject.get("APPLY_STATE");
    if (applyState.equals(BEFORE_WAITING_CONTENT)) {
      applyState = AFTER_WAITING_CONTENT;
    }
    return applyState;
  }

}
