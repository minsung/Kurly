package ms.study.kurly.domain.terms;

import lombok.RequiredArgsConstructor;
import ms.study.kurly.common.Response;
import ms.study.kurly.domain.terms.dto.Agreement;
import ms.study.kurly.domain.terms.dto.TermsResponse;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TermsService {

    private final TermsRepository termsRepository;
    private final TermsAgreementRepository termsAgreementRepository;

    public Response<List<TermsResponse>> terms() {

         List<TermsResponse> list = termsRepository.findAll().stream()
                 .map(Terms::toResponse)
                 .toList();

        return Response.<List<TermsResponse>>builder()
                .data(list)
                .build();
    }

    public Boolean checkAllRequiredAgreements(List<Agreement> agreements) {

        Set<Agreement> agreementSet = new HashSet<>(agreements);
        Set<Terms> termsSet = new HashSet<>(termsRepository.findAll());

        Set<Long> requiredIds = termsSet.stream()
                .filter(Terms::getRequired)
                .map(Terms::getId)
                .collect(Collectors.toSet());

        return !agreementSet.stream()
                .filter(agreement -> requiredIds.contains(agreement.getId()))
                .allMatch(Agreement::getAgreed);
    }

    public void saveAgreements(Agreement[] agreements, String email) {

        Arrays.stream(agreements)
                .forEach(agreement -> {
                    termsAgreementRepository.findByEmailAndTermsId(email, agreement.getId())
                            .ifPresentOrElse(termsAgreement -> {
                                termsAgreement.setAgreed(agreement.getAgreed());
                                termsAgreementRepository.save(termsAgreement);
                            }, () -> {
                                termsAgreementRepository.save(TermsAgreement.builder()
                                        .email(email)
                                        .agreed(agreement.getAgreed())
                                        .termsId(agreement.getId())
                                        .build());
                            });
                });
    }
}
