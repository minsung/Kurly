package ms.study.kurly.domain.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.study.kurly.common.Error;
import ms.study.kurly.common.Response;
import ms.study.kurly.common.exception.KurlyException;
import ms.study.kurly.domain.jwt.TokenService;
import ms.study.kurly.domain.terms.TermsService;
import ms.study.kurly.domain.user.dto.LoginRequest;
import ms.study.kurly.domain.user.dto.LoginResponse;
import ms.study.kurly.domain.user.dto.SignupRequest;
import ms.study.kurly.domain.verification.VerificationTokenService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    private final UserDetailsServiceImpl userDetailsService;
    private final TokenService tokenService;

    private final BCryptPasswordEncoder passwordEncoder;

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

        termsService.saveAgreements(dto.getAgreements(), dto.getEmail());

        userRepository.save(User.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
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

    public Response<LoginResponse> login(LoginRequest dto) {

        User user = userDetailsService.authenticate(dto.getEmail(), dto.getPassword());
        String refreshToken = tokenService.createNewRefreshToken(user);
        String accessToken = tokenService.createNewAccessToken(refreshToken);

        return Response.<LoginResponse>builder()
                .data(new LoginResponse(accessToken, refreshToken))
                .build();
    }
}
