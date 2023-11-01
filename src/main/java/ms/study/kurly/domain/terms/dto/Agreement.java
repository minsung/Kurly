package ms.study.kurly.domain.terms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "이용 약관 동의 내용")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter(AccessLevel.PRIVATE)
public class Agreement {

    @Schema(description = "이용 약관 ID")
    private Long id;

    @Schema(description = "이용 약관 동의 여부")
    private Boolean agreed;
}
