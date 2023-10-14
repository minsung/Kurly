package ms.study.kurly.common.validator;

import jakarta.validation.Constraint;
import jakarta.validation.constraints.Pattern;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {})
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Pattern(regexp = "(01[016789])(\\d{3,4})(\\d{4})")
public @interface MobileNumber {

        String message() default "휴대폰 번호가 유효하지 않습니다.";

        Class<?>[] groups() default {};

        Class<?>[] payload() default {};
}
