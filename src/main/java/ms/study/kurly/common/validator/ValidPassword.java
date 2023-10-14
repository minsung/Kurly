package ms.study.kurly.common.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {

        String message() default "비밀번호가 유효하지 않습니다.";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
}
