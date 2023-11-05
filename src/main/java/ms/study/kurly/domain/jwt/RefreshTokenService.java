package ms.study.kurly.domain.jwt;

import lombok.RequiredArgsConstructor;
import ms.study.kurly.common.Error;
import ms.study.kurly.common.exception.KurlyException;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken findByRefreshToken(String refreshToken) {

        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> {
                    Error error = Error.REFRESH_TOKEN_NOT_FOUND;
                    Map<Object, Object> data = Map.of("refreshToken", refreshToken);

                    return new KurlyException(error, data);
                });
    }

    public void save(Long userId, String token) {

        refreshTokenRepository.save(new RefreshToken(userId, token));
    }

}
