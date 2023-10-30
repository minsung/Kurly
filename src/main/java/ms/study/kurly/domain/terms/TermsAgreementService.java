package ms.study.kurly.domain.terms;

import lombok.RequiredArgsConstructor;
import ms.study.kurly.common.Error;
import ms.study.kurly.common.exception.KurlyException;
import ms.study.kurly.domain.terms.dto.TermsAgreementRequest;
import ms.study.kurly.domain.verification.MobileVerification;
import ms.study.kurly.domain.verification.MobileVerificationRepository;
import ms.study.kurly.domain.verification.VerificationToken;
import ms.study.kurly.domain.verification.VerificationTokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TermsAgreementService {

    private final TermsRepository termsRepository;
    private final TermsAgreementRepository termsAgreementRepository;
    private final MobileVerificationRepository mobileVerificationRepository;
    private final VerificationTokenRepository verificationTokenRepository;

    public void agreement(TermsAgreementRequest request) {

        VerificationToken token = verificationTokenRepository.findByToken(request.getMobileVerificationToken())
                .orElseThrow(() -> {
                    Error error = Error.MOBILE_VERIFICATION_TOKEN_NOT_FOUND;
                    Map<Object, Object> data = Map.of("request", request);

                    return new KurlyException(error, data);
                });

        Long mobileVerificationId = token.getVerificationId();
        MobileVerification verification = mobileVerificationRepository.findById(mobileVerificationId)
                .orElseThrow(() -> {
                    Error error = Error.MOBILE_VERIFICATION_TOKEN_NOT_FOUND;
                    Map<Object, Object> data = Map.of("request", request);

                    return new KurlyException(error, data);
                });

        if (!verification.getVerificationToken().getToken().equals(request.getMobileVerificationToken())) {
            Error error = Error.MOBILE_VERIFICATION_CODE_NOT_MATCH;
            Map<Object, Object> data = Map.of("request", request);

            throw new KurlyException(error, data);
        }

        List<Terms> termsList = termsRepository.findAll();
        List<TermsAgreement> agreements = termsAgreementRepository.findByEmail(request.getEmail());

        termsList.forEach(terms -> {
            TermsAgreementRequest.Agreement requestAgreement = Arrays.stream(request.getAgreements())
                    .filter(a -> a.getId().equals(terms.getId()))
                    .findFirst()
                    .orElseThrow(() -> {
                        Error error = Error.TERMS_NOT_FOUND;
                        Map<Object, Object> data = Map.of("request", request);

                        return new KurlyException(error, data);
                    });

            if (terms.getRequired() && !requestAgreement.getAgreed()) {
                Error error = Error.REQUIRED_TERMS_NOT_AGREED;
                Map<Object, Object> data = Map.of("request", request);

                throw new KurlyException(error, data);
            }

            Optional<TermsAgreement> existingAgreement = agreements.stream()
                    .filter(agreement -> agreement.getTerms().getId().equals(terms.getId()))
                    .findFirst();

            if (existingAgreement.isPresent()) {
                existingAgreement.get().setAgreed(requestAgreement.getAgreed());
                termsAgreementRepository.save(existingAgreement.get());
            } else {
                termsAgreementRepository.save(TermsAgreement.builder()
                        .email(request.getEmail())
                        .agreed(requestAgreement.getAgreed())
                        .terms(terms)
                        .build());
            }
        });
    }
}
