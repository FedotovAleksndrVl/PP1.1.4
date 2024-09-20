package jm.task.core.jdbc.util;
import java.sql.*;


import java.util.Properties;
import java.util.logging.Level;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class Util {
    private final static String URL = "jdbc:mysql://localhost:3306/DB";
    private final static String USERNAME = "admin";
    private final static String PASSWORDS = "admin";
    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final static String DIALECT = "org.hibernate.dialect.MySQLDialect";

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {

        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.WARNING);

        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.put(Environment.DRIVER, DRIVER);
                settings.put(Environment.URL, URL);
                settings.put(Environment.USER, USERNAME);
                settings.put(Environment.PASS, PASSWORDS);
                settings.put(Environment.DIALECT, DIALECT);
                settings.put(Environment.SHOW_SQL, "false");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "");
                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public static Connection getConnect () {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORDS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
