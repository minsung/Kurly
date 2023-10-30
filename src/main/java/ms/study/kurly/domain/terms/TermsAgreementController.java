package ms.study.kurly.domain.terms;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ms.study.kurly.domain.terms.dto.TermsAgreementRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TermsAgreementController {

    private final TermsAgreementService service;

    @PostMapping("/terms/agreement")
    public void agreement(@Valid @RequestBody TermsAgreementRequest request) {

        service.agreement(request);
    }
}
