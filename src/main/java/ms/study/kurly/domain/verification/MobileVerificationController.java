package ms.study.kurly.domain.verification;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ms.study.kurly.common.Response;
import ms.study.kurly.domain.verification.dto.MobileCodeVerifyRequest;
import ms.study.kurly.domain.verification.dto.MobileCodeVerifyResponse;
import ms.study.kurly.domain.verification.dto.MobileCodeRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "휴대폰 인증")
@RequiredArgsConstructor
@RestController
public class MobileVerificationController {

    private final MobileVerificationService mobileVerificationService;

    @Operation(summary = "휴대폰 인증 코드 발송")
    @PostMapping("/verification/mobile-verification-code")
    @ResponseStatus(HttpStatus.CREATED)
    public void mobileVerificationCode(@Valid @RequestBody MobileCodeRequest request) {

        mobileVerificationService.sendVerificationCode(request);
    }

    @Operation(summary = "휴대폰 인증 코드 검증")
    @PostMapping("/verification/mobile-verification-code/verify")
    public Response<MobileCodeVerifyResponse> verifyMobileVerificationCodeRequest(@Valid @RequestBody MobileCodeVerifyRequest request) {

        return mobileVerificationService.verifyMobileVerificationCode(request);
    }
}
