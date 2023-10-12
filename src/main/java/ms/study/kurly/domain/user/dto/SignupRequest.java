package ms.study.kurly.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import ms.study.kurly.domain.user.User;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(AccessLevel.PRIVATE)
public class SignupRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    private String mobileNumber;

    public User toEntity() {
        return User.builder()
                .email(email)
                .password(password)
                .name(name)
                .mobileNumber(mobileNumber)
                .build();
    }
}
