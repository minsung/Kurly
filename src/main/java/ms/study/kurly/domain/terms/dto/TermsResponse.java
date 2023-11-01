package ms.study.kurly.domain.terms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "이용 약관 응답")
@Data
@Builder
@Setter(AccessLevel.PRIVATE)
public class TermsResponse {

    @Schema(description = "이용 약관 ID")
    private Long id;

    @Schema(description = "이용 약관 제목")
    private String title;

    @Schema(description = "이용 약관 내용")
    private String content;

    @Schema(description = "이용 약관 필수 동의 여부")
    private Boolean required;
}
