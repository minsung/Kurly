package ms.study.kurly.domain.verification.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter(AccessLevel.PRIVATE)
public class MobileCodeVerifyResponse {

    private String uuid;
}
