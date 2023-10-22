package ms.study.kurly.domain.verification;

import lombok.RequiredArgsConstructor;
import ms.study.kurly.common.Error;
import ms.study.kurly.common.Response;
import ms.study.kurly.common.exception.KurlyException;
import ms.study.kurly.domain.verification.dto.MobileCodeVerifyRequest;
import ms.study.kurly.domain.verification.dto.MobileCodeVerifyResponse;
import ms.study.kurly.domain.verification.dto.MobileCodeRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class MobileVerificationService {

    private final MobileVerificationRepository mobileVerificationRepository;

    public void sendVerificationCode(MobileCodeRequest dto) {

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

    public Response<MobileCodeVerifyResponse> verifyMobileVerificationCode(MobileCodeVerifyRequest dto) {

        MobileVerification verification = mobileVerificationRepository
                .findByMobileNumberOrderByCreatedAtDesc(dto.getMobileNumber())
                .orElseThrow(() -> {
                    Error error = Error.MOBILE_VERIFICATION_CODE_NOT_MATCH;
                    Map<Object, Object> data = Map.of("request", dto);

                    return new KurlyException(error, data);
                });

        if (!verification.getVerificationCode().equals(dto.getVerificationCode())) {
            Error error = Error.MOBILE_VERIFICATION_CODE_NOT_MATCH;
            Map<Object, Object> data = Map.of("request", dto);

            throw new KurlyException(error, data);
        }

        if (verification.getCreatedAt().plusMinutes(3).isAfter(LocalDateTime.now())) {
            Error error = Error.EXPIRED_MOBILE_VERIFICATION_CODE;
            Map<Object, Object> data = Map.of("request", dto);

            throw new KurlyException(error, data);
        }

        String uuid = UUID.randomUUID().toString();
        verification.setHashValue(uuid);
        verification.setVerified(true);

        return Response.<MobileCodeVerifyResponse>builder()
                .data(new MobileCodeVerifyResponse(uuid))
                .build();
    }
}
