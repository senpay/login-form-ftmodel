package business.validation;

import java.util.Optional;

/**
 * Created by Alexander Pushkarev on 24.12.19.
 */
public class EmptyStringValidator implements IFieldValidator {

    @Override
    public String getFieldName() {
        return "Login";
    }

    public Optional<ValidationError> validate(String strToValidate) {
        if(strToValidate == null || strToValidate.isEmpty()) {
            return ValidationError.getOptionalValidationError(
                    getFieldName() + " cannot be empty");
        }
        return ValidationError.getEmptyValidationError();
    }

}
