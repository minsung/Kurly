package ms.study.kurly.domain.jwt.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "액세스 토큰 발급 요청")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter(AccessLevel.PRIVATE)
public class CreateAccessTokenRequest {

    @Schema(description = "리프레시 토큰")
    private String refreshToken;
}
