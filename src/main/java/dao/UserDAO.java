package dao;

import exeption.DBException;
import model.User;
import java.util.List;

public interface UserDAO {

    List<User> getAllUser() throws DBException;

    User getUserById(long id) throws DBException;

    boolean validateUser (long id, String name, String password) throws DBException;

    long getCountUserThisCity(String city) throws DBException;

    void updateUserById(long id, User newParameterUser) throws DBException;

    void addUser(User user) throws DBException;

    void deleteUserById (long id) throws DBException;
}
