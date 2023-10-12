# User (사용자)
# 사용자 정보를 나타내는 엔티티입니다.

CREATE TABLE user (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  email VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  name VARCHAR(255) NOT NULL,
  mobileNumber VARCHAR(20) NOT NULL
);