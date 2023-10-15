package ms.study.kurly.common;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter(AccessLevel.PRIVATE)
public class Response<T> {

    private Integer code;
    private String message;
    private T data;
}
