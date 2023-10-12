package ms.study.kurly.domain.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.study.kurly.domain.user.dto.LoginRequest;
import ms.study.kurly.domain.user.dto.SignupRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void signup(SignupRequest dto) {

        User save = userRepository.save(dto.toEntity());
    }

    public Boolean isExistEmail(String email) {

        return userRepository.existsByEmail(email);
    }

    public Boolean login(LoginRequest dto) {

        return userRepository.existsByEmailAndPassword(dto.getEmail(), dto.getPassword());
    }
}
