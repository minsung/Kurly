package ms.study.kurly.domain.terms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Schema(description = "이용 약관 동의 요청")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter(AccessLevel.PRIVATE)
public class TermsAgreementRequest {

    @Schema(description = "이용 약관 동의 목록")
    @NotBlank
    private Agreement[] agreements;

    @Schema(description = "이메일 주소", defaultValue = "aa@bb.cc")
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")
    private String email;

    @Schema(description = "휴대폰 인증 응답으로 전달받은 위변조 방지 토큰")
    @NotBlank
    private String mobileVerificationToken;

    @Schema(description = "이용 약관 동의 내용")
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Setter(AccessLevel.PRIVATE)
    public static class Agreement {

        @Schema(description = "이용 약관 ID")
        private Long id;

        @Schema(description = "이용 약관 동의 여부")
        private Boolean agreed;
    }
}
