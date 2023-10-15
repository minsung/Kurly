package ms.study.kurly.common.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MobileNumberConstraintValidator implements ConstraintValidator<MobileNumber, String> {

    private static final String MOBILE_NUMBER_PATTERN = "(01[016789])(\\d{3,4})(\\d{4})";
    private static final java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(MOBILE_NUMBER_PATTERN);

    @Override
    public boolean isValid(String mobileNumber, ConstraintValidatorContext context) {
        return pattern.matcher(mobileNumber).matches();
    }
}