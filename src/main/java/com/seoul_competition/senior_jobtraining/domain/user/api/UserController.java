package com.seoul_competition.senior_jobtraining.domain.user.api;

import com.seoul_competition.senior_jobtraining.domain.user.dto.request.UserDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

  @Value("${myapp.api-key}")
  private String SECRET_KEY;
  private static final long EXPIRATION_TIME = 31536000L; // 1년
  private static final int COOKIE_MAX_AGE = 31536000; // 1년

  @PostMapping
  public ResponseEntity<Void> giveCookies(HttpServletResponse response,
      @RequestBody @Valid UserDto userDto) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("gender", userDto.gender());
    claims.put("age", userDto.age());
    claims.put("location", userDto.location());
    claims.put("interest", userDto.interest());

    String jwt = Jwts.builder()
        .addClaims(claims)
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .signWith(SignatureAlgorithm.HS512, SECRET_KEY.getBytes())
        .compact();

    // JWT를 쿠키로 변환합니다.
    Cookie jwtCookie = new Cookie("jwt", jwt);
    jwtCookie.setMaxAge(COOKIE_MAX_AGE);
    jwtCookie.setHttpOnly(true);
    jwtCookie.setSecure(false);
    jwtCookie.setPath("/");
    // jwtCookie.setDomain("");

    // SameSite=Strict 속성을 추가합니다.
    String cookieString = String.format("%s=%s; Max-Age=%d; Path=/; HttpOnly; SameSite=Strict",
        jwtCookie.getName(),
        jwtCookie.getValue(), jwtCookie.getMaxAge());
    response.addHeader("Set-Cookie", cookieString);

    // 쿠키를 응답에 추가합니다.
    response.addCookie(jwtCookie);
    return ResponseEntity.noContent().build();
  }
}
