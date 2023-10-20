package ms.study.kurly.domain.verification.dto;

import lombok.*;
import ms.study.kurly.common.validator.MobileNumber;

@Data
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(AccessLevel.PRIVATE)
public class MobileVerificationCodeRequest {

    @MobileNumber
    private String mobileNumber;
}
