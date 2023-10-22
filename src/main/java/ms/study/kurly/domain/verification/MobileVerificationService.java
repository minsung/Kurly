package ms.study.kurly.domain.verification;

import lombok.RequiredArgsConstructor;
import ms.study.kurly.common.Error;
import ms.study.kurly.common.exception.KurlyException;
import ms.study.kurly.domain.verification.dto.MobileVerificationCodeRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class MobileVerificationService {

    private final MobileVerificationRepository mobileVerificationRepository;

    public void sendVerificationCode(MobileVerificationCodeRequest dto) {

        List<MobileVerification> verifications = mobileVerificationRepository
                .findTop3ByMobileNumberOrderByCreatedAtDesc(dto.getMobileNumber());

        if (verifications.size() == 3) {
            MobileVerification verification = verifications.get(2);
            if (verification.getCreatedAt().plusMinutes(1).isAfter(LocalDateTime.now())) {
                Error error = Error.EXCEEDED_VERIFICATION_CODE_REQUEST_LIMIT;
                Map<Object, Object> data = Map.of("request", dto);

                throw new KurlyException(error, data);
            }
        }

        String verificationCode = String.valueOf((int) (Math.random() * 1000000));

        MobileVerification mobileVerification = MobileVerification.builder()
                .mobileNumber(dto.getMobileNumber())
                .verificationCode(verificationCode)
                .build();

        mobileVerificationRepository.save(mobileVerification);
    }
}
