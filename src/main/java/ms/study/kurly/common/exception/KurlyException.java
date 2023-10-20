package ms.study.kurly.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ms.study.kurly.common.Error;

import java.util.Map;

@Getter
@RequiredArgsConstructor
public class KurlyException extends RuntimeException {

    private final Error error;
    private final Map<Object, Object> data;
}
