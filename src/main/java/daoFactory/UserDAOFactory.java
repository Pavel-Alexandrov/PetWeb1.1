package daoFactory;

import DAO.UserDao;

import java.io.IOException;

public interface UserDAOFactory {
    public UserDao getUserDAO() throws IOException;
}
