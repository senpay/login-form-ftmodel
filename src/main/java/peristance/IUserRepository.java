package peristance;

import java.util.List;

import business.User;

/**
 * Created by Alexander Pushkarev on 24.12.19.
 */
public interface IUserRepository {

    void saveUser(User userToSave);
    List<User> getUsers();

}
