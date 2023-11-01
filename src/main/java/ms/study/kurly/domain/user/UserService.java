package ms.study.kurly.domain.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.study.kurly.common.Error;
import ms.study.kurly.common.exception.KurlyException;
import ms.study.kurly.domain.terms.TermsService;
import ms.study.kurly.domain.user.dto.LoginRequest;
import ms.study.kurly.domain.user.dto.SignupRequest;
import ms.study.kurly.domain.verification.VerificationTokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final VerificationTokenService verificationTokenService;

    private final TermsService termsService;

    public void signup(SignupRequest dto) {

        if (!verificationTokenService.verifyToken(dto.getMobileVerificationToken())) {
            Error error = Error.VERIFICATION_TOKEN_NOT_MATCH;
            Map<Object, Object> data = Map.of("request", dto);

            throw new KurlyException(error, data);
        }

        checkExistEmail(dto.getEmail());

        if (termsService.checkAllRequiredAgreements(List.of(dto.getAgreements()))) {
            Error error = Error.REQUIRED_TERMS_NOT_AGREED;
            Map<Object, Object> data = Map.of("request", dto);

            throw new KurlyException(error, data);
        }

        // TODO: 존재하면 업데이트 해야 하는데 방법을 생각해야 함
        termsService.saveAgreements(dto.getAgreements(), dto.getEmail());

        userRepository.save(User.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .name(dto.getName())
                .mobileNumber(dto.getMobileNumber())
                .build());
    }

    public void checkExistEmail(String email) {

        if (userRepository.existsByEmail(email)) {
            Error error = Error.EMAIL_ALREADY_EXISTS;
            Map<Object, Object> data = Map.of("email", email);

            throw new KurlyException(error, data);
        }
    }

    public void login(LoginRequest dto) {

       boolean hasUser = userRepository.existsByEmailAndPassword(dto.getEmail(), dto.getPassword());

       if (!hasUser) {
           Error error = Error.USER_NOT_FOUND;
           Map<Object, Object> data = Map.of("request", dto);

           throw new KurlyException(error, data);
       }
    }
}
