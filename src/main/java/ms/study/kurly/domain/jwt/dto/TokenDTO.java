package ms.study.kurly.domain.jwt.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter(AccessLevel.PRIVATE)
public class TokenDTO {

    private Long userId;

    private String email;
}
