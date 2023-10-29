package ms.study.kurly.domain.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ms.study.kurly.common.Error;
import ms.study.kurly.domain.user.dto.LoginRequest;
import ms.study.kurly.domain.user.dto.SignupRequest;
import ms.study.kurly.common.exception.KurlyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "회원")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원가입")
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void signup(@Valid @RequestBody SignupRequest request) {

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            Error error = Error.PASSWORD_NOT_MATCH;
            Map<Object, Object> data = Map.of("request", request);

            throw new KurlyException(error, data);
        }

        userService.signup(request);
    }

    @Operation(summary = "이메일 중복 체크")
    @Parameter(name = "email", description = "이메일")
    @GetMapping("/email-exists")
    public void checkExistEmail(@RequestParam String email) {

         userService.checkExistEmail(email);
    }

    @Operation(summary = "로그인")
    @PostMapping("/login")
    public void login(@Valid @RequestBody LoginRequest dto) {

        userService.login(dto);
    }
}
