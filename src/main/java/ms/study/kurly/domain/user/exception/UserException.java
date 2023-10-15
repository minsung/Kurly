package ms.study.kurly.domain.user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ms.study.kurly.common.Error;

@Getter
@RequiredArgsConstructor
public class UserException extends RuntimeException {

    private final Error error;
}
