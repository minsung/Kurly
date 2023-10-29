package ms.study.kurly.domain.verification.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import ms.study.kurly.common.validator.MobileNumber;

@Schema(description = "휴대폰 번호 인증 코드 발송 요청")
@Data
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(AccessLevel.PRIVATE)
public class MobileCodeRequest {

    @Schema(description = "휴대폰 번호", defaultValue = "01012345678")
    @MobileNumber
    private String mobileNumber;
}
