package ms.study.kurly.domain.jwt;

import lombok.RequiredArgsConstructor;
import ms.study.kurly.common.Error;
import ms.study.kurly.common.config.jwt.TokenProvider;
import ms.study.kurly.common.exception.KurlyException;
import ms.study.kurly.domain.jwt.dto.TokenDTO;
import ms.study.kurly.domain.user.User;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;

    public String createNewAccessToken(String refreshToken) {

        if (!tokenProvider.validToken(refreshToken)) {
            Error error = Error.INVALID_REFRESH_TOKEN;
            Map<Object, Object> data = Map.of("refreshToken", refreshToken);

            throw new KurlyException(error, data);
        }

        return tokenProvider.generateToken(refreshToken, Duration.ofHours(2));
    }

    public String createNewRefreshToken(User user) {

        String token = tokenProvider.generateToken(new TokenDTO(user.getId(), user.getEmail()), Duration.ofDays(14));

        refreshTokenService.save(user.getId(), token);

        return token;
    }
}
