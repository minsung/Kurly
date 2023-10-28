package ms.study.kurly.domain.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.study.kurly.common.Error;
import ms.study.kurly.domain.terms.Terms;
import ms.study.kurly.domain.terms.TermsAgreement;
import ms.study.kurly.domain.terms.TermsAgreementRepository;
import ms.study.kurly.domain.terms.TermsRepository;
import ms.study.kurly.domain.user.dto.LoginRequest;
import ms.study.kurly.domain.user.dto.SignupRequest;
import ms.study.kurly.common.exception.KurlyException;
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
    private final TermsAgreementRepository termsAgreementRepository;
    private final TermsRepository termsRepository;

    public void signup(SignupRequest dto) {

        int agreementsCount = termsAgreementRepository.findByEmail(dto.getEmail()).size();
        int termsCount = termsRepository.findAll().size();

        if (agreementsCount != termsCount) {
            Error error = Error.TERMS_NOT_FOUND;
            Map<Object, Object> data = Map.of("request", dto);

            throw new KurlyException(error, data);
        }

        checkExistEmail(dto.getEmail());

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
