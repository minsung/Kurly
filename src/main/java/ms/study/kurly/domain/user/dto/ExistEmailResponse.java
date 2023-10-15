package ms.study.kurly.domain.user.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter(AccessLevel.PRIVATE)
public class ExistEmailResponse {

    private Boolean isExist;
}
