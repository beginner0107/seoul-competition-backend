# Build Stage
FROM gradle:7.6-jdk17-alpine as TEMP_BUILD_IMAGE

# 환경변수 설정
ENV APP_HOME=/usr/app/

# 경로 설정
WORKDIR $APP_HOME

# build.gradle, settings.gradle, gradle 복사
COPY build.gradle settings.gradle $APP_HOME
COPY gradle $APP_HOME/gradle
COPY --chown=gradle:gradle . /home/gradle/src

# 권한 변경
USER root

RUN chown -R gradle /home/gradle/src

# gradle build 명령어를 실행 > 빌드 실패시 에러 무시
RUN gradle build || return 0

# 코드 복사 > 새로 애플리케이션 빌드
COPY . .
RUN gradle clean build -x test

# Run Stage
FROM openjdk:17.0.2-jdk

# 환경변수 설정
ENV APP_HOME=/usr/app/
ENV ARTIFACT_NAME=app.jar

# 경로 설정
WORKDIR $APP_HOME

# Build Stage에서 만든 JAR 파일 복사
COPY --from=TEMP_BUILD_IMAGE $APP_HOME/build/libs/$ARTIFACT_NAME .

# 포트번호
EXPOSE 8080

# JAR 파일 실행
ENTRYPOINT exec java -jar ${ARTIFACT_NAME}