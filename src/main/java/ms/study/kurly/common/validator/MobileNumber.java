package ms.study.kurly.common.validator;

import jakarta.validation.Constraint;
import jakarta.validation.constraints.Pattern;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MobileNumberConstraintValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface MobileNumber {

        String message() default "휴대폰 번호가 유효하지 않습니다.";

        Class<?>[] groups() default {};

        Class<?>[] payload() default {};
}
