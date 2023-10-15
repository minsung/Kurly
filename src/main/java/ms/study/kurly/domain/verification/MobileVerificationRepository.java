package ms.study.kurly.domain.verification;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MobileVerificationRepository extends JpaRepository<MobileVerification, Long> {

//        Boolean existsByMobileNumber(String mobileNumber);
//
//        Boolean existsByMobileNumberAndVerificationCode(String mobileNumber, String verificationCode);
//
//        MobileNumberVerification findByMobileNumber(String mobileNumber);
}
