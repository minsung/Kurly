package ms.study.kurly.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Error {

    EXCEEDED_VERIFICATION_CODE_REQUEST_LIMIT(1000, "요청 횟수를 초과했습니다. 잠시 후 다시 시도해 주세요.", "1분 이내 인증번호 3회 요청 초과"),
    MOBILE_VERIFICATION_CODE_NOT_MATCH(1001, "인증코드를 확인해주세요.", "인증 코드 불일치"),
    EXPIRED_MOBILE_VERIFICATION_CODE(1002, "인증코드가 만료되었습니다.", "3분이 지난 후 인증 코드 확인"),
    INVALID_EMAIL(1003, "이메일을 확인해주세요.", "이메일 형식 오류"),
    INVALID_PASSWORD(1004, "비밀번호를 확인해주세요.", "비밀번호 형식 오류"),
    INVALID_MOBILE_NUMBER(1005, "휴대폰 번호를 확인해주세요.", "휴대폰 번호 형식 오류"),
    PASSWORD_NOT_MATCH(1006, "비밀번호를 확인해주세요.", "비밀번호 불일치"),
    EMAIL_ALREADY_EXISTS(1007, "이메일을 확인해주세요.", "이미 존재하는 이메일"),
    UNKNOWN(9999, "오류가 발생했습니다.", "알 수 없는 오류");

    private final Integer code;
    private final String message;
    private final String detailMessage;
}
