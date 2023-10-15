# User (사용자)
# 사용자 정보를 나타내는 엔티티입니다.

CREATE TABLE user (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  email VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  name VARCHAR(255) NOT NULL,
  mobile_number VARCHAR(20) NOT NULL
);

# Mobile verification (휴대폰 인증)
# 휴대폰 번호 인증 정보를 나타내는 엔티티입니다.

CREATE TABLE mobile_verification (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  mobile_number VARCHAR(20) NOT NULL,
  verification_code VARCHAR(6) NOT NULL,
  hash_value VARCHAR(64),
  is_verified BIT NOT NULL DEFAULT FALSE,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);
