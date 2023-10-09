package ms.study.kurly.feature.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ms.study.kurly.feature.user.model.SignupModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public void signup(@Valid @RequestBody SignupModel request) {

        userService.signup(request);
    }

    @GetMapping("/exist/email")
    public Boolean isExistEmail(@RequestParam String email) {

        return userService.isExistEmail(email);
    }
}
