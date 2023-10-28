package ms.study.kurly.common.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.study.kurly.common.Error;
import ms.study.kurly.common.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class KurlyExceptionHandler {

    private final ObjectMapper objectMapper;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<Void>> handleKurlyException(KurlyException e) throws JsonProcessingException {

        String data = objectMapper.writeValueAsString(e.getData());

        log.info("code: {}, message: {}, data: {}", e.getError().getCode(), e.getError().getDetailMessage(), data, e);

        return ResponseEntity.status(e.getError().getStatus()).body(Response.<Void>builder()
                .code(e.getError().getCode())
                .message(e.getError().getMessage())
                .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        BindingResult bi = e.getBindingResult();
        Error error = getErrorCode(bi);

        log.error("code: {}, message: {}", error.getCode(), error.getDetailMessage(), e);

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
