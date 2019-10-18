package service;

import DAO.UserDao;
import exception.StatementException;
import model.User;

import java.util.List;

public class UserService {

    public List<User> getAllUsers() throws StatementException {
        UserDao userDAO = getUserDAO();
        return userDAO.getAllUsers();
    }

    //сперва проверяет наличие юзера в базе по логину
    //если нет, то добавляет, иначе ничего не делает
    public void addUser(String name, String login, String password) throws StatementException {
        UserDao userDAO = getUserDAO();
        if (!userDAO.checkUser(login)) {
            userDAO.addUser(name, login, password);
        }
    }

    //сперва проверяет наличие юзера в базе по логину
    //если есть, меняет данные, иначе ничего не делает
    public void updateUser(String name, String login, String password) throws StatementException {
        UserDao userDAO = getUserDAO();
        if (userDAO.checkUser(login)) {
            userDAO.updateUser(name, login, password);
        }
    }

    public void deleteUser(Integer id) throws StatementException {
        UserDao userDAO = getUserDAO();
        userDAO.deleteUser(id);
    }

    public void cleanUp() throws StatementException {
        UserDao userDAO = getUserDAO();
        userDAO.dropTable();
    }

    private UserDao getUserDAO() {
        return UserDao.getInstance();
    }
}
