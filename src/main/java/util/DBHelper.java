package util;

import model.User;
import org.hibernate.cfg.Configuration;
import service.implementation.UserService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
    private static volatile DBHelper dbHelper;

    public static DBHelper getInstance() {

        DBHelper instance = dbHelper;

        if (instance==null) {
            synchronized (DBHelper.class) {
                instance = dbHelper;
                if (instance==null) {
                    dbHelper = instance = new DBHelper();
                }
            }
        }
        return instance;
    }

    private DBHelper() {}

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?characterEncoding=utf8&user=root&password=root&serverTimezone=UTC");
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    public Configuration getConfiguration() {
        Configuration configuration = new Configuration();

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/mydb?characterEncoding=utf8&serverTimezone=UTC");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "root");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        configuration.addAnnotatedClass(UserService.class);
        configuration.addAnnotatedClass(User.class);
        return configuration;
    }
}
