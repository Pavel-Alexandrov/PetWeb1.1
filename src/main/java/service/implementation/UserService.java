package service.implementation;

import daoFactory.implementation.UserDAOFactory;
import DAO.UserDao;
import model.User;

import java.io.IOException;
import java.util.List;

public class UserService implements service.UserService {


    private static volatile UserService userService;

    public static UserService getInstance() {

        UserService instance = userService;

        if (instance==null) {
            synchronized (UserService.class) {
                instance = userService;
                if (instance==null) {
                    userService = instance = new UserService();
                }
            }
        }
        return instance;
    }

    private UserService() {}

    public List<User> getAllUsers() throws IOException {
        UserDAOFactory userDAOFactory = new UserDAOFactory();
        UserDao userDAO = userDAOFactory.getUserDAO();
        return userDAO.getAllUsers();
    }

    //сперва проверяет наличие юзера в базе по логину
    //если нет, то добавляет, иначе ничего не делает
    public void addUser(String name, String login, String password) throws IOException {
        UserDAOFactory userDAOFactory = new UserDAOFactory();
        UserDao userDAO = userDAOFactory.getUserDAO();
        if (!userDAO.checkUser(login)) {
            userDAO.addUser(name, login, password);
        }
    }

    //сперва проверяет наличие юзера в базе по логину
    //если есть, меняет данные, иначе ничего не делает
    public void updateUser(String name, String login, String password) throws IOException {
        UserDAOFactory userDAOFactory = new UserDAOFactory();
        UserDao userDAO = userDAOFactory.getUserDAO();
        if (userDAO.checkUser(login)) {
            userDAO.updateUser(name, login, password);
        }
    }

    public void deleteUser(Integer id) throws IOException {
        UserDAOFactory userDAOFactory = new UserDAOFactory();
        UserDao userDAO = userDAOFactory.getUserDAO();
        userDAO.deleteUser(id);
    }

    public void cleanUp() throws IOException {
        UserDAOFactory userDAOFactory = new UserDAOFactory();
        UserDao userDAO = userDAOFactory.getUserDAO();
        userDAO.dropTable();
    }
}
