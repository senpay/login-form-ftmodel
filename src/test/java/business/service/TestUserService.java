package business.service;

import business.User;
import business.validation.ValidationError;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import peristance.IUserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TestUserService {

    private UserService sut;
    private String userName = "userName";

    private class InMemoryRepository implements IUserRepository {

        @Override
        public void saveUser(User userToSave) {}

        @Override
        public void clean() {}

        @Override
        public List<User> getUsers() {
            final User user = new User();
            user.setUserLogInName(userName);
            return Arrays.asList(user);
        }
    }

    @Before
    public void setUp() {
        sut = new UserService();
        sut.setUserRepository(new InMemoryRepository());
    }

    @Test
    public void shouldNotBeAbleToDuplicatedUser() {
        final List<String> users = sut.getUserInfoList();
        Optional<ValidationError> validationStatus = sut.validateLogin(userName);
        Assert.assertTrue(validationStatus.isPresent());
        Assert.assertEquals(userName + " user is already present", validationStatus.get().getErrorMessage());
    }
}

