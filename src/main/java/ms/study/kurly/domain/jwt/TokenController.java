package ms.study.kurly.domain.jwt;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import ms.study.kurly.domain.jwt.dto.CreateAccessTokenRequest;
import ms.study.kurly.domain.jwt.dto.CreateAccessTokenResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "토큰")
@RequiredArgsConstructor
@RestController
public class TokenController {

    private final TokenService tokenService;

    @Operation(summary = "액세스 토큰 발급")
    @PostMapping("/token")
    public CreateAccessTokenResponse createAccessToken(@RequestBody CreateAccessTokenRequest request) {

        String newAccessToken = tokenService.createNewAccessToken(request.getRefreshToken());

        return new CreateAccessTokenResponse(newAccessToken);
    }
}
