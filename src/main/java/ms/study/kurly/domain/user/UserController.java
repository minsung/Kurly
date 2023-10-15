package ms.study.kurly.domain.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ms.study.kurly.common.Response;
import ms.study.kurly.domain.user.dto.ExistEmailResponse;
import ms.study.kurly.domain.user.dto.LoginRequest;
import ms.study.kurly.domain.user.dto.SignupRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void signup(@Valid @RequestBody SignupRequest request) {

        userService.signup(request);
    }

    @GetMapping("/email-exists")
    public Response<ExistEmailResponse> isExistEmail(@RequestParam String email) {

        ExistEmailResponse response = ExistEmailResponse.builder()
                .isExist(userService.isExistEmail(email))
                .build();

        return Response.<ExistEmailResponse>builder()
                .data(response)
                .build();
    }

    @PostMapping("/login")
    public Boolean login(@Valid @RequestBody LoginRequest dto) {

        return userService.login(dto);
    }
}
