package ms.study.kurly.domain.verification;

import lombok.RequiredArgsConstructor;
import ms.study.kurly.common.Response;
import ms.study.kurly.domain.verification.dto.MobileVerificationCodeRequest;
import ms.study.kurly.domain.verification.dto.MobileVerificationCodeResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MobileVerificationController {

    private final MobileVerificationService mobileVerificationService;

    @PostMapping("/verification/mobile-verification-code")
    public Response<MobileVerificationCodeResponse> mobileVerificationCode(@RequestBody MobileVerificationCodeRequest request) {

        MobileVerification verification = mobileVerificationService.getVerificationCode(request);
        MobileVerificationCodeResponse response = MobileVerificationCodeResponse.builder()
                .id(verification.getId())
                .verificationCode(verification.getVerificationCode())
                .build();

        return Response.<MobileVerificationCodeResponse>builder()
                .data(response)
                .build();
    }
}
