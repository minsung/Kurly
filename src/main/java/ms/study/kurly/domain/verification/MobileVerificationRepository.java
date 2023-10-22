package ms.study.kurly.domain.verification;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MobileVerificationRepository extends JpaRepository<MobileVerification, Long> {

    List<MobileVerification> findTop3ByMobileNumberOrderByCreatedAtDesc(String mobileNumber);
}
