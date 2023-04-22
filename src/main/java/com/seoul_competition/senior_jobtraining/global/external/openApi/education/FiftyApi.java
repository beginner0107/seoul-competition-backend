package com.seoul_competition.senior_jobtraining.global.external.openApi.education;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import lombok.Getter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class FiftyApi {

  private static final String OPENAPI_URL =
      "http://openapi.seoul.go.kr:8088/%s/json/FiftyPotalEduInfo/1/999";

  @Value("${key.fifty}")
  private String key;

  private Long totalCount;
  private JSONObject subResult;
  private JSONArray infoArr;


  public void getJson() {
    try {
      URL url = new URL(String.format(OPENAPI_URL, key));
      BufferedReader bf;
      bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
      String result = bf.readLine();
      jsonPasring(result);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void jsonPasring(String result) throws ParseException {
    JSONParser jsonParser = new JSONParser();
    JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
    JSONObject CardSubwayStatsNew = (JSONObject) jsonObject.get("FiftyPotalEduInfo");

    totalCount = (Long) CardSubwayStatsNew.get("list_total_count");
    subResult = (JSONObject) CardSubwayStatsNew.get("RESULT");
    infoArr = (JSONArray) CardSubwayStatsNew.get("row");
  }
}
