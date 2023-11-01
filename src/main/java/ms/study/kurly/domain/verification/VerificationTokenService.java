package ms.study.kurly.domain.verification;

import lombok.RequiredArgsConstructor;
import ms.study.kurly.common.Error;
import ms.study.kurly.common.exception.KurlyException;
import ms.study.kurly.domain.verification.dto.VerificationTokenRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class VerificationTokenService {

    private final VerificationTokenRepository verificationTokenRepository;

    public VerificationToken generate(VerificationTokenRequest request) {

        String token = UUID.randomUUID().toString();

        VerificationToken verificationToken = VerificationToken.builder()
                .type(request.getType())
                .token(token)
                .verificationId(request.getVerificationId())
                .build();

        return verificationTokenRepository.save(verificationToken);
    }

    public Boolean verifyToken(String token) {

        VerificationToken verificationToken = verificationTokenRepository.findByToken(token)
                .orElseThrow(() -> {
                    Error error = Error.VERIFICATION_TOKEN_NOT_FOUND;
                    Map<Object, Object> data = Map.of("request", token);

                    return new KurlyException(error, data);
                });

        return verificationToken.getToken().equals(token);
    }
}
