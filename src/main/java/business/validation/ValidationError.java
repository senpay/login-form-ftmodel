package business.validation;

import java.util.Optional;

/**
 * Created by Alexander Pushkarev on 24.12.19.
 */
public class ValidationError {

    private String errorMessage;

    private ValidationError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * To be used for non empty error code
     * @param errorMessage
     * @return
     */
    public static Optional<ValidationError> getOptionalValidationError(String errorMessage) {
        return Optional.of(new ValidationError(errorMessage));
    }

    /**
     * To be used if no errors were spotted
     * @return
     */
    public static Optional<ValidationError> getEmptyValidationError() {
        return Optional.empty();
    }
}
