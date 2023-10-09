package ms.study.kurly.feature.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.study.kurly.feature.user.model.SignupModel;
import ms.study.kurly.feature.user.model.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    public void signup(@Valid SignupModel model) {

        User save = userRepository.save(mapper.toEntity(model));
    }

    public Boolean isExistEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
