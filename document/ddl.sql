# Kurly DB
# Kurly DB는 MySQL을 사용합니다.

CREATE DATABASE kurly CHARACTER SET utf8 COLLATE utf8_bin;

# Dev user (개발용 사용자)
# 개발용 사용자는 localhost에서만 접근 가능합니다.

CREATE USER 'dev'@'192.168.65.1' IDENTIFIED WITH mysql_native_password BY '1q2w3e';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, INDEX, DROP, ALTER, REFERENCES, CREATE TEMPORARY TABLES, LOCK TABLES ON kurly.* TO 'dev'@'192.168.65.1';
GRANT FILE ON *.* TO 'dev'@'192.168.65.1';
FLUSH PRIVILEGES;

# User (사용자)
# 사용자 정보를 나타내는 엔티티입니다.

CREATE TABLE user
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    email         VARCHAR(255)                           NOT NULL,
    password      VARCHAR(255)                           NOT NULL,
    name          VARCHAR(255)                           NOT NULL,
    mobile_number VARCHAR(20)                            NOT NULL,
    status        ENUM ('ACTIVE', 'INACTIVE', 'DELETED') NOT NULL DEFAULT 'ACTIVE',
    created_at    DATETIME                               NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    DATETIME                               NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE INDEX idx_email ON user (email);

# Mobile verification (휴대폰 인증)
# 휴대폰 번호 인증 정보를 나타내는 엔티티입니다.

CREATE TABLE mobile_verification
(
    id                BIGINT AUTO_INCREMENT PRIMARY KEY,
    mobile_number     VARCHAR(20) NOT NULL,
    verification_code VARCHAR(6)  NOT NULL,
    hash_value        VARCHAR(64),
    is_verified       BIT         NOT NULL DEFAULT FALSE,
    created_at        DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at        DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE INDEX idx_mobile_number ON mobile_verification (mobile_number);