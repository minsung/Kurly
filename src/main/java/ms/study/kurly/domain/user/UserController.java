package ms.study.kurly.domain.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ms.study.kurly.domain.user.dto.LoginRequest;
import ms.study.kurly.domain.user.dto.SignupRequest;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public void signup(@Valid @RequestBody SignupRequest request) {

        userService.signup(request);
    }

    @GetMapping("/exist/email")
    public Boolean isExistEmail(@RequestParam String email) {

        return userService.isExistEmail(email);
    }

    @PostMapping("/login")
    public Boolean login(@Valid @RequestBody LoginRequest dto) {

        return userService.login(dto);
    }
}
