package environment;

import business.service.UserService;
import peristance.IUserRepository;
import peristance.SqliteUserRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alexander Pushkarev on 24.12.19.
 */
public class UserApplication {


    private static IUserRepository repository = new SqliteUserRepository();
    private static UserService service = new UserService(); {
        service.setUserRepository(repository);
    }


    public Map<String, Object> getUsersList() {
        return getUsersList("N/A");
    }

    public Map<String, Object> addUser(final String username) {
        final String status = service.addUser(username);
        final Map<String, Object> model = getUsersList(status);
        return model;
    }

    private Map<String, Object> getUsersList(String status) {
        final Map<String, Object> model = new HashMap<>();
        model.put("status", status);
        model.put("users", service.getUserInfoList());
        return model;
    }
}
