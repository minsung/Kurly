package ms.study.kurly.common.exception;

import ms.study.kurly.common.Error;
import ms.study.kurly.common.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class KurlyExceptionHandler {

    @ExceptionHandler(KurlyException.class)
    public ResponseEntity<Response<Void>> handleUserException(KurlyException e) {

        HttpStatus status = switch (e.getError()) {
            case EXCEEDED_VERIFICATION_CODE_REQUEST_LIMIT -> HttpStatus.TOO_MANY_REQUESTS;
            case EMAIL_ALREADY_EXISTS -> HttpStatus.CONFLICT;
            case PASSWORD_NOT_MATCH -> HttpStatus.BAD_REQUEST;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };

        return ResponseEntity.status(status).body(Response.<Void>builder()
                .code(e.getError().getCode())
                .message(e.getError().getMessage())
                .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        // TODO: 에러 로그 추가
        BindingResult bi = e.getBindingResult();
        Error error = getErrorCode(bi);

        return Response.<Void>builder()
                .code(error.getCode())
                .message(error.getMessage())
                .build();
    }

    private static Error getErrorCode(BindingResult bi) {

        String code = bi.getFieldError() != null ? bi.getFieldError().getCode() : null;
        Error error;

        if (code == null) return Error.UNKNOWN;

        error = switch (code) {
            case "Email" -> Error.INVALID_EMAIL;
            case "Password" -> Error.INVALID_PASSWORD;
            case "MobileNumber" -> Error.INVALID_MOBILE_NUMBER;
            default -> Error.UNKNOWN;
        };

        return error;
    }
}
