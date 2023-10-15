package ms.study.kurly.domain.verification.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(AccessLevel.PRIVATE)
public class MobileVerificationCodeResponse {

    private Long id;
    private String verificationCode;
}
