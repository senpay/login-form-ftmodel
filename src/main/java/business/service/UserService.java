package business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import business.User;
import business.validation.IFieldValidator;
import business.validation.EmptyStringValidator;
import business.validation.LoginValidator;
import business.validation.ValidationError;
import peristance.IUserRepository;

/**
 * Created by Alexander Pushkarev on 24.12.19.
 */
public class UserService {

    private IUserRepository userRepository;

    /**
     * return status string, which is either success or error of some kind
     *
     * @param login
     * @return
     */
    public String addUser(String login) {
        Optional<ValidationError> validationError =
                validateFields(login);
        if (validationError.isPresent()) {
            return validationError.get().getErrorMessage();
        }

        User user = buildUser(login);
        userRepository.saveUser(user);
        return "user " + login + " was created";
    }

    public List<String> getUserInfoList() {
        List<User> users = userRepository.getUsers();
        List<String> userInfo = new ArrayList<>();
        for(User user : users) {
            //Yes, writing code THAT bad. Brute force and no lambdas
            userInfo.add("Name: " + user.getUserLogInName());

        }
        return userInfo;
    }

    public void setUserRepository(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private Optional<ValidationError> validateFields(String login) {
        Optional<ValidationError> loginValidationError = validateLogin(login);
        if (loginValidationError.isPresent()) {
            return loginValidationError;
        }

        return ValidationError.getEmptyValidationError();

    }

    private Optional<ValidationError> validateLogin(String login) {
        IFieldValidator emptyStringValidator = new EmptyStringValidator();
        IFieldValidator loginValidator = new LoginValidator(this.userRepository);

        Optional<ValidationError> loginValidationError = emptyStringValidator.validate(login);
        if (loginValidationError.isPresent()) {
            return loginValidationError;
        }

        return loginValidator.validate(login);
    }

    private User buildUser(String login) {
        User user = new User();
        user.setUserLogInName(login);
        return user;
    }
}
