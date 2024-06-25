# 🍒 git convention

<details>
<summary> 표준 컨벤션 </summary>

```
[필수사항 : 제목]    **type[영어]: Subject[한글]** 
                     //한 줄을 띄워 분리
[선택사항 : 본문]    **body**       
                     //한 줄을 띄워 분리
[선택사항: 꼬릿말]   **footer** 
```

### 한글로 제목을 작성 하는 경우

```
"고침", "추가", "변경" 등의 명령조로 시작
 
 ex) feat: "추가 get data api 함수"
```

### 본문 (body)

1. 한 줄 당 72자 이내
2. 아무리 길어도 괜찮으니, 최대한 상세히 작성
3. 무엇을, 왜 변경했는지 작성 (코드 자체를 상세히 적는 것은 지양)

**예)** 로그인 기능 구현을 위해 로그인 요청을 보내는 axios 함수 작성

### 꼬리말 (footer)

1. 꼬리말은 어디까지나 선택사항입니다. (없어도 무방합니다)
2. "유형: 이슈번호" 형식으로 작성
3. 유형은 “Close, Fix, Resolve” 등을 활용 (보통 Close 는 일반 개발 이슈를 닫을 때, Fix 는 버그 이슈를 닫을 때, Resolve 는 문의나 요청사항에 대한 이슈를 닫을 때 사용합니다.)

```
# 예시1

Resolve: *#123*
이슈 123을 해결
Fixes : 이슈 수정중, Resolve : 이슈 해결, Ref : 참고할 이슈,
Related to : 해당 커밋에 관련된 이슈번호(아직 해결되지 않은 경우)

# 예시2 

Fixes : #45 Related to : 해당 커밋에 관련된 이슈번호
```

### 전체 예시

```
feat: "추가 get data api 함수"      ····· 타입: 제목

로그인 API 개발           ····· 본문
Resolves: #123           ····· 꼬리말 ▶ 이슈 123을 해결했으며,
Ref: #456                ····· 이슈 456 를 참고해야하며,
Related to: #48, #45     ····· 현재 커밋에서 아직 이슈 48 과 45 가 해결되지 않았다
```

</details>

---

<details>
<summary> 샘플 커밋 메세지 </summary> 


<span style = "color:#FA4F92; font-size: large; font-weight: bold">
Feat (기능 추가)
</span> 

**새로운 기능을 프로젝트에 추가하는 경우**


```
Feat: 사용자 프로필 사진 업로드 기능 추가
Feat: 검색 결과 페이지네이션 구현
Feat: 댓글 기능 도입
Feat: 다국어 지원 기능 추가
```

<span style = "color:#FA4F92; font-size: large; font-weight: bold">
Fix (버그 수정)
</span>

**발견된 버그나 문제를 수정하는 경우**

```
Fix: 로그인 시 세션 만료 버그 수정
Fix: 결제 페이지 404 오류 해결
Fix: 이메일 중복 확인 로직 오류 수정
Fix: 모바일 뷰에서 UI 깨짐 현상 수정
```

<span style = "color:#FA4F92; font-size: large; font-weight: bold">
Refactor (리팩토링)
</span>

**코드를 재구성하거나 개선하는 경우, 기능의 변경 없이 코드의 가독성이나 성능을 향상**

```
Refactor: 유저 서비스 클래스 리팩토링
Refactor: API 응답 구조 통일성 개선
Refactor: 중복 코드 제거 및 모듈화 진행
Refactor: 데이터베이스 접근 로직 최적화
```

<span style = "color:#FA4F92; font-size: large; font-weight: bold">
Docs (문서 수정)
</span>

**프로젝트의 문서를 추가, 수정, 삭제하는 작업**

```
Docs: API 문서 업데이트
Docs: 개발 환경 설정 가이드 추가
Docs: 프로젝트 설치 및 실행 방법 작성
Docs: 변경 로그 업데이트
```

<span style = "color:#FA4F92; font-size: large; font-weight: bold">
Style (코드 스타일)
</span>

**코드 포맷 변경, 세미콜론 누락, 코드 수정이 없는경우 .코드 스타일, 포맷팅, 누락된 세미콜론 추가 등 기능에 영향을 주지 않는 스타일 변경사항.**

```
Style: ESLint 규칙에 따른 코드 포맷팅 수정
Style: 코드 주석 스타일 통일
Style: 불필요한 CSS 클래스 제거
Style: 불필요한 세미콜론 제거
```

<span style = "color:#FA4F92; font-size: large; font-weight: bold">
Test (테스트)
</span>

**새로운 테스트를 추가하거나 기존 테스트를 수정하는 경우**

```
Test: 신규 로그인 기능 테스트 코드 추가
Test: 쇼핑 카트 추가 기능 유닛 테스트 작성
Test: 데이터베이스 연결 테스트 코드 갱신
Test: 성능 테스트 스크립트 업데이트
```

<span style = "color:#FA4F92; font-size: large; font-weight: bold">
Chore (기타 작업)
</span>

**빌드 태스크, 패키지 매니저 설정 같은 코드나 로직 변경 없이 기타 작업을 수행하는 경우**

```
Chore: 의존성 라이브러리 업데이트
Chore: 빌드 스크립트 수정
Chore: .gitignore 파일 업데이트
Chore: 린트 규칙 업데이트
```

<span style = "color:#FA4F92; font-size: large; font-weight: bold">
Design (디자인 변경)
</span>

**사용자 인터페이스(UI)나 사용자 경험(UX) 개선을 위한 디자인 변경 사항**

```
Design: 로그인 페이지 UI 개선
Design: 대시보드 레이아웃 업데이트
Design: 테마 색상 스키마 변경
Design: 모바일 뷰 반응형 디자인 최적화
```

<span style = "color:#FA4F92; font-size: large; font-weight: bold">
Rename (이름 변경)
</span>

**변수, 함수, 파일, 디렉토리 등의 이름 변경 사항**

```
Rename: 사용자 모델 변수명 변경
Rename: login 함수를 authenticateUser로 변경
Rename: old_project 디렉토리를 new_project로 변경
Rename: outdatedComponent.jsx를 updatedComponent.jsx로 변경
```

<span style = "color:#FA4F92; font-size: large; font-weight: bold">
Remove (제거)
</span>

**더 이상 사용되지 않는 코드, 파일, 라이브러리 등의 제거**

```
Remove: 사용되지 않는 유틸리티 함수 제거
Remove: 레거시 코드 파일 삭제
Remove: deprecated 라이브러리 제거
Remove: 불필요한 CSS 스타일 규칙 제거
```

<span style = "color:#FA4F92; font-size: large; font-weight: bold">
CI (Continuous Integration)
</span>

**지속적 통합 시스템에 관련된 변경 사항**

```
CI: Jenkins 파이프라인 설정 추가
CI: GitLab CI/CD 설정 업데이트
CI: 테스트 자동화 스크립트 수정
CI: 빌드 프로세스 최적화
```

<span style = "color:#FA4F92; font-size: large; font-weight: bold">
Build (빌드 시스템)
</span>

**빌드 스크립트나 외부 종속성과 같은 빌드 시스템에 대한 변경사항**

```
Build: Webpack 설정 업데이트
Build: Gradle 버전 업그레이드
Build: Docker 이미지 생성 과정 최적화
Build: 라이브러리 의존성 관리 개선
```

<span style = "color:#FA4F92; font-size: large; font-weight: bold">
Perf (성능 개선)
</span>

**애플리케이션의 성능을 향상시키는 변경 사항**

```
Perf: 이미지 로딩 지연 최소화
Perf: 데이터베이스 쿼리 속도 개선
Perf: 페이지 렌더링 시간 단축
Perf: 메모리 사용량 최적화
```

<span style = "color:#FA4F92; font-size: large; font-weight: bold">
Security (보안 강화)
</span>

**보안 취약점을 해결하거나 보안 관련 기능을 추가하는 경우**

```
Security: XSS 공격 방어 로직 강화
Security: 데이터 암호화 방식 개선
Security: API 접근 권한 검증 로직 추가
Security: 취약한 의존성 업데이트
```

<span style = "color:#FA4F92; font-size: large; font-weight: bold">
Config (설정 변경)
</span>

**프로젝트 설정이나 환경 변수 변경과 같은 구성 변경**

```
Config: .env 파일 설정 항목 추가
Config: 로깅 레벨 조정
Config: 써드 파티 서비스 API 키 업데이트
Config: 개발 환경과 운영 환경 설정 분리
```

</details>

---

<details>
<summary> 그 외 참고사항 </summary>
<br>
<img src="docs/gitMessage/gitConvention.png" alt="" style="width: 700px">



</details>

