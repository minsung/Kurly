package ms.study.kurly.domain.terms.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter(AccessLevel.PRIVATE)
public class TermsAgreementRequest {

    @NotBlank
    private Agreement[] agreements;

    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")
    private String email;

    @NotBlank
    private String mobileVerificationToken;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Setter(AccessLevel.PRIVATE)
    public static class Agreement {

        private Long id;
        private Boolean agreed;
    }
}
