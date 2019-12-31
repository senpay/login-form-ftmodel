package environment;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Created by Alexander Pushkarev on 24.12.19.
 */
public class UserApplicationTest {

    private UserApplication sut;

    @Before
    public void setUp() {
       sut = new UserApplication();
       sut.deleteAllUsers();
    }

    @Test
    @Ignore
    public void shouldBeAbleToAddNewUser() {
        final Map<String, Object> myCoolNewUser = sut.addUser("MyCoolNewUser");
        Assert.assertEquals("user MyCoolNewUser was created", myCoolNewUser.get("status"));
        Assert.assertTrue(((List) myCoolNewUser.get("users")).contains("Name: MyCoolNewUser"));
    }

    @Test
    public void shouldNotBeAbleToAddEmptyUserName() {
        final Map<String, Object> usersBeforeTest = sut.getUsersList();
        final int numberOfUsersBeforeTheTest = ((List) usersBeforeTest.get("users")).size();
        final Map<String, Object> myCoolNewUser = sut.addUser("");
        Assert.assertEquals("Login cannot be empty", myCoolNewUser.get("status"));
        Assert.assertEquals(numberOfUsersBeforeTheTest, ((List) myCoolNewUser.get("users")).size());
    }
}
