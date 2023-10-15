package ms.study.kurly.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import ms.study.kurly.common.validator.MobileNumber;
import ms.study.kurly.common.validator.Password;
import ms.study.kurly.domain.user.User;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(AccessLevel.PRIVATE)
public class SignupRequest {

    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")
    private String email;

    @Password
    private String password;

    @NotBlank
    private String confirmPassword;

    @NotBlank
    private String name;

    @MobileNumber
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
