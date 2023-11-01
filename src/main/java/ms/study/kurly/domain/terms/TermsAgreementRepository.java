package ms.study.kurly.domain.terms;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TermsAgreementRepository extends JpaRepository<TermsAgreement, Long> {

    List<TermsAgreement> findByEmail(String email);

    Optional<TermsAgreement> findByEmailAndTermsId(String email, Long termsId);
}
