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
    public void addUser(String name, String login, String password, String role) throws IOException {
        UserDAOFactory userDAOFactory = new UserDAOFactory();
        UserDao userDAO = userDAOFactory.getUserDAO();
        if (!userDAO.checkUser(login)) {
            userDAO.addUser(name, login, password, role);
        }
    }

    //сперва проверяет наличие юзера в базе по логину
    //если есть, меняет данные, иначе ничего не делает
    //пользователя с ролью "admin" нельзя изменить
    public void updateUser(String name, String login, String password, String role) throws IOException {
        UserDAOFactory userDAOFactory = new UserDAOFactory();
        UserDao userDAO = userDAOFactory.getUserDAO();
        if (!role.equals("admin") && userDAO.checkUser(login)) {
            userDAO.updateUser(name, login, password, role);
        }
    }

    //пользователя с ролью "admin" нельзя удалить
    public void deleteUser(Integer id) throws IOException {
        UserDAOFactory userDAOFactory = new UserDAOFactory();
        UserDao userDAO = userDAOFactory.getUserDAO();
        if (id != 1) {
            userDAO.deleteUser(id);
        }
    }

    public void cleanUp() throws IOException {
        UserDAOFactory userDAOFactory = new UserDAOFactory();
        UserDao userDAO = userDAOFactory.getUserDAO();
        userDAO.dropTable();
    }
}
