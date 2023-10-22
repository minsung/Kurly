package ms.study.kurly.domain.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.study.kurly.common.Error;
import ms.study.kurly.domain.user.dto.LoginRequest;
import ms.study.kurly.domain.user.dto.SignupRequest;
import ms.study.kurly.common.exception.KurlyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void signup(SignupRequest dto) {

        isExistEmail(dto.getEmail());
        userRepository.save(dto.toEntity());
    }

    public void isExistEmail(String email) {

        if (userRepository.existsByEmail(email)) {
            Error error = Error.EMAIL_ALREADY_EXISTS;
            Map<Object, Object> data = Map.of("email", email);

            throw new KurlyException(error, data);
        }
    }

    public Boolean login(LoginRequest dto) {

        return userRepository.existsByEmailAndPassword(dto.getEmail(), dto.getPassword());
    }
}
