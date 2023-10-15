package ms.study.kurly.domain.verification.dto;

import lombok.*;

@Data
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(AccessLevel.PRIVATE)
public class MobileVerificationCodeRequest {

    private String mobileNumber;
}
