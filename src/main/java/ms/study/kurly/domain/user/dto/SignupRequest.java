package ms.study.kurly.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import ms.study.kurly.common.validator.MobileNumber;
import ms.study.kurly.common.validator.Password;
import ms.study.kurly.domain.terms.dto.Agreement;

@Schema(description = "회원가입 요청")
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(AccessLevel.PRIVATE)
public class SignupRequest {

    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")
    @Schema(description = "ID로 사용할 이메일 주소", defaultValue = "aa@bb.cc")
    private String email;

    @Password
    @Schema(description = "비밀번호 (대문자, 소문자, 숫자, 특수문자 각 한자리 이상 포함, 8자 이상)", defaultValue = "Aa12345!")
    private String password;

    @NotBlank
    @Schema(description = "비밀번호 확인", defaultValue = "Aa12345!")
    private String confirmPassword;

    @NotBlank
    @Schema(description = "이름", defaultValue = "홍길동")
    private String name;

    @MobileNumber
    @Schema(description = "휴대폰 번호", defaultValue = "01012345678")
    private String mobileNumber;

    @Schema(description = "이용 약관 동의 목록")
    private Agreement[] agreements;

    @NotBlank
    @Schema(description = "휴대폰 인증 응답으로 전달받은 위변조 방지 토큰")
    private String mobileVerificationToken;
}
