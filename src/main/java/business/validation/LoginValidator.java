package business.validation;

import business.User;
import peristance.IUserRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Alexander Pushkarev on 24.12.19.
 */
public class LoginValidator implements IFieldValidator {

    private IUserRepository repository;

    public LoginValidator(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public String getFieldName() {
        return "Login";
    }

    public Optional<ValidationError> validate(String strToValidate) {
        final List<User> users = repository.getUsers();
        for (User user : users) {
            if (strToValidate.equals(user.getUserLogInName())) {
                return ValidationError.getOptionalValidationError(strToValidate +
                        " user is already present");
            }
        }
        return ValidationError.getEmptyValidationError();
    }

}
