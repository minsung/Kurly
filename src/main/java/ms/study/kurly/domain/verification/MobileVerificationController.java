package ms.study.kurly.domain.verification;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ms.study.kurly.domain.verification.dto.MobileVerificationCodeRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MobileVerificationController {

    private final MobileVerificationService mobileVerificationService;

    @PostMapping("/verification/mobile-verification-code")
    @ResponseStatus(HttpStatus.CREATED)
    public void mobileVerificationCode(@Valid @RequestBody MobileVerificationCodeRequest request) {

        mobileVerificationService.sendVerificationCode(request);
    }
}
