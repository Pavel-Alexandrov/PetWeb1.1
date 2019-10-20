package DAO;

import util.DBHelper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UserDAOFactory {

    public static UserDao getUserDAO() throws IOException {

        FileInputStream fileInputStream;
        Properties properties = new Properties();

        try {
            fileInputStream = new FileInputStream("C:\\c\\java\\PreProject1.2\\src\\main\\resources\\config.properties");
            properties.load(fileInputStream);

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
