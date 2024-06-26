# Dockerfile

# 베이스 이미지 정의
FROM openjdk:17-jdk-alpine as builder

# 작업 디렉토리 설정
WORKDIR /app

# 소스 코드 복사
COPY . .

# Gradle을 이용한 빌드
RUN chmod +x ./gradlew
RUN ./gradlew build

# 런타임 이미지 생성
FROM openjdk:17-jdk-alpine

# 빌드된 JAR 파일 복사
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080
# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]

