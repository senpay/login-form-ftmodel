import com.codeborne.selenide.Configuration;
import org.junit.*;

import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;

/**
 * Created by Alexander Pushkarev on 24.12.19.
 */
public class LoginFormTest {

    private SelenideMainPage sut = SelenideMainPage.INSTANCE;
    private static final String APPLICATION_URL = "http://localhost:4567/index";

    @BeforeClass
    public static void setUpClass() {
        final String[] args = {};
        Main.main(args);
        Configuration.browser = "firefox";
    }

    @Before
    public void setUp() {
        open(APPLICATION_URL);
    }

    @After
    public void tearDown() {
        close();
    }

    @Test
    public void shouldBeAbleToAddNewUser() {
        sut.setUserName("MyCoolNewUser");
        sut.clickSubmit();
        Assert.assertEquals("Status: user MyCoolNewUser was created", sut.getStatus());
        Assert.assertTrue(sut.getUsers().contains("Name: MyCoolNewUser"));
    }

    @Test
    public void shouldNotBeAbleToAddEmptyUseName() {
        final int numberOfUsersBeforeTheTest = sut.getUsers().size();
        sut.clickSubmit();
        Assert.assertEquals("Status: Login cannot be empty", sut.getStatus());
        Assert.assertEquals(numberOfUsersBeforeTheTest, sut.getUsers().size());
    }
}
