package ms.study.kurly.domain.verification.dto;

import lombok.*;
import ms.study.kurly.common.validator.MobileNumber;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter(AccessLevel.PRIVATE)
public class MobileCodeVerifyRequest {

    @MobileNumber
    private String mobileNumber;
    private String verificationCode;
}
