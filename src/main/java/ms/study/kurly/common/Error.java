package ms.study.kurly.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Error {

    INVALID_EMAIL(1001, "이메일 형식 오류"),
    INVALID_PASSWORD(1002, "비밀번호 형식 오류"),
    INVALID_MOBILE_NUMBER(1003, "휴대폰 번호 형식 오류"),
    PASSWORD_NOT_MATCH(1004, "비밀번호 불일치"),
    EMAIL_ALREADY_EXISTS(1005, "이미 존재하는 이메일"),
    UNKNOWN(999, "알 수 없는 오류");

    private final Integer code;
    private final String message;
}
