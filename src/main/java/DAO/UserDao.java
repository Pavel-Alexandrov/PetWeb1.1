package DAO;

import model.User;

import java.util.List;

public interface UserDao {

    public List<User> getAllUsers();

    public User getUserByLogin(String login);

    public void addUser(String name, String login, String password, String role);

    public void updateUser(String name, String login, String password, String role);

    public void deleteUser(Integer id);

    //проверяет наличие юзера в базе по логину
    public boolean checkUser(String login);

    public void dropTable();
}
