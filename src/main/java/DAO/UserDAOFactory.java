package DAO;

import util.DBHelper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UserDAOFactory {

    public UserDao getUserDAO() throws IOException {

        InputStream inputStream;
        Properties properties = new Properties();
        String propFileName = "config.properties";

        try {
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("не нашел путь к файду properties");
            }

            String daoType = properties.getProperty("daoType");

            switch (daoType) {
                case "JDBC":
                    return new UserJDBCDAO(DBHelper.getInstance().getConnection());
                case "HQL":
                    return new UserHQLDAO(DBHelper.getInstance().getConfiguration());
                default:
                    throw new IOException("в конфигурационном файле не задан daoType");
            }
        } catch (IOException ioe) {
            throw new IOException("ошибка при создании эксземпляра DAO");
        }
    }
}
