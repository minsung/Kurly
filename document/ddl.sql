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
    updated_at    DATETIME                               NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_email (email)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;



# Verification tokens (인증 토큰)
# 인증 토큰 정보를 나타내는 엔티티입니다.

CREATE TABLE verification_token
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    type            ENUM ('MOBILE') NOT NULL DEFAULT 'MOBILE',
    token           VARCHAR(64)     NOT NULL,
    created_at      DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    verification_id BIGINT,
    INDEX idx_token(token),
    INDEX idx_verification_id (verification_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

# Mobile verification (휴대폰 인증)
# 휴대폰 번호 인증 정보를 나타내는 엔티티입니다.

CREATE TABLE mobile_verification
(
    id                    BIGINT AUTO_INCREMENT PRIMARY KEY,
    mobile_number         VARCHAR(20) NOT NULL,
    verification_code     VARCHAR(6)  NOT NULL,
    created_at            DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at            DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    verification_token_id BIGINT,
    INDEX idx_mobile_number (mobile_number),
    INDEX idx_verification_token (verification_token_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

# Terms (약관)
# 약관 정보를 나타내는 엔티티입니다.

CREATE TABLE terms
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    title      VARCHAR(255) NOT NULL,
    content    TEXT         NOT NULL,
    required   BIT          NOT NULL DEFAULT FALSE,
    created_at DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO terms (title, content, required, created_at, updated_at)
VALUES ('필수 약관 1', '필수 약관 1 내용', TRUE, NOW(), NOW());
INSERT INTO terms (title, content, required, created_at, updated_at)
VALUES ('필수 약관 2', '필수 약관 2 내용', TRUE, NOW(), NOW());
INSERT INTO terms (title, content, required, created_at, updated_at)
VALUES ('선택 약관 1', '선택 약관 1 내용', FALSE, NOW(), NOW());

# Terms of service (이용약관)
# 이용약관 동의 정보를 나타내는 엔티티입니다.

CREATE TABLE terms_agreement
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    email      VARCHAR(255) NOT NULL,
    agreed     BIT          NOT NULL DEFAULT FALSE,
    created_at DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    terms_id   BIGINT,
    INDEX idx_email (email),
    INDEX idx_terms_id (terms_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

# JWT refresh tokens (JWT 리프레시 토큰)
# JWT 리프레시 토큰 정보를 나타내는 엔티티입니다.

CREATE TABLE refresh_token
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    refresh_token VARCHAR(255) NOT NULL,
    created_at    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    user_id       BIGINT,
    INDEX idx_user_id (user_id),
    INDEX idx_refresh_token (refresh_token)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

# Category (카테고리)
# 상품 카테고리 정보를 나타내는 엔티티입니다.

CREATE TABLE categories
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    created_at DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_name (name)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

# Product (상품)
# 상품 정보를 나타내는 엔티티입니다.

CREATE TABLE products
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    name           VARCHAR(255)                NOT NULL,
    description    TEXT                        NOT NULL,
    supply_price   BIGINT                      NOT NULL,
    consumer_price BIGINT                      NOT NULL,
    sale_price     BIGINT                      NOT NULL,
    stock_quantity BIGINT                      NOT NULL,
    status         ENUM ('ACTIVE', 'INACTIVE') NOT NULL DEFAULT 'INACTIVE',
    category_id    BIGINT                      NOT NULL,
    discount_id    BIGINT                      NOT NULL,
    created_at     DATETIME                    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at     DATETIME                    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FULLTEXT INDEX idx_fulltext_search (name, description),
    INDEX idx_category_id (category_id),
    INDEX discount_id (discount_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

# Discount (할인)
# 할인 정보를 나타내는 엔티티입니다.

CREATE TABLE discounts
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    type       ENUM ('PERCENTAGE', 'AMOUNT') NOT NULL DEFAULT 'PERCENTAGE',
    name       VARCHAR(255)                  NOT NULL,
    value      BIGINT                        NOT NULL,
    status     ENUM ('ACTIVE', 'INACTIVE')   NOT NULL DEFAULT 'INACTIVE',
    started_at DATETIME                      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ended_at   DATETIME                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_at DATETIME                      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_name (name)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;