package peaksoft.dao;

import peaksoft.entities.User;

import java.util.List;

public interface UserDao{
    void saveUser(User user);
    User getUserById(long id);
    List<User> getAllUsers();
    void deleteUser(Long id);
    void updateUser(User user,Long id);
    void updateCarAndUser(Long id);
}
