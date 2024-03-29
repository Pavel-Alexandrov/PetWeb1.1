package service;

import model.User;

import java.io.IOException;
import java.util.List;

public interface UserService {
    public List<User> getAllUsers() throws IOException;

    public void addUser(String name, String login, String password) throws IOException;

    public void updateUser(String name, String login, String password) throws IOException;

    public void deleteUser(Integer id) throws IOException;

    public void cleanUp() throws IOException;
}
