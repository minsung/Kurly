package ms.study.kurly.domain.user;

import lombok.RequiredArgsConstructor;
import ms.study.kurly.common.Error;
import ms.study.kurly.common.exception.KurlyException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User loadUserByUsername(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    Error error = Error.USER_NOT_FOUND;
                    Map<Object, Object> data = Map.of("email", email);

                    return new KurlyException(error, data);
                });
    }

    public User authenticate(String email, String password) {

        User user = loadUserByUsername(email);
        boolean matchedPassword = passwordEncoder.matches(password, user.getPassword());

        if (!matchedPassword) {
            Error error = Error.INVALID_USER;
            Map<Object, Object> data = Map.of("email", email, "password", password);

            throw new KurlyException(error, data);
        }

        return user;
    }
}
