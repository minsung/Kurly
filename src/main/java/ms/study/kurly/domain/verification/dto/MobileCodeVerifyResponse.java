package ms.study.kurly.domain.verification.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "휴대폰 번호 인증 코드 검증 응답")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter(AccessLevel.PRIVATE)
public class MobileCodeVerifyResponse {

    @Schema(description = "회원 가입시 위변조 방지 토큰")
    private String token;
}
