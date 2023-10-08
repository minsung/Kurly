package ms.study.kurly.feature.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ms.study.kurly.feature.user.model.SignupModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public void signup(@Valid @RequestBody SignupModel request) {
        userService.signup(request);
    }
}
