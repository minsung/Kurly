# API 스펙

## 회원 가입

새로운 사용자를 등록합니다.

### 엔드포인트
- **요청 메서드**: POST
- **URL**: `{{url}}/signup`
- **Content-Type**: `application/json`

### 요청 바디 (Request Body)
```json
{
  "email": "email",
  "password": "password",
  "name": "name",
  "mobileNumber": "mobileNumber"
}
```

### 응답
- **성공**: HTTP 상태 코드 200
- **실패**: HTTP 상태 코드 400

## 중복된 이메일 존재 여부 확인

입력한 이메일 주소가 이미 존재하는지 확인합니다.

### 엔드포인트
- **요청 메서드**: GET
- **URL**: `{{url}}/exist/email?email={email}`
- **Content-Type**: `application/json`

### 요청 바디 (Request Body)
없음

### 응답
- **성공**: HTTP 상태 코드 200
    - **이메일 존재**: `true`
    - **이메일 미존재**: `false`
- **실패**: HTTP 상태 코드 400