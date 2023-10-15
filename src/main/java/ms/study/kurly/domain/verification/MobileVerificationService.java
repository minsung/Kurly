package ms.study.kurly.domain.verification;

import lombok.RequiredArgsConstructor;
import ms.study.kurly.domain.verification.dto.MobileVerificationCodeRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MobileVerificationService {

    private final MobileVerificationRepository mobileVerificationRepository;

    public MobileVerification getVerificationCode(MobileVerificationCodeRequest dto) {

        String verificationCode = String.valueOf((int) (Math.random() * 1000000));

        MobileVerification mobileVerification = MobileVerification.builder()
                .mobileNumber(dto.getMobileNumber())
                .verificationCode(verificationCode)
                .build();

        return mobileVerificationRepository.save(mobileVerification);
    }
}
