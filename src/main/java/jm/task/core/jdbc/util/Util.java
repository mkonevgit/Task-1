package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public static Connection createConnection(String connectionString, String username, String password) throws SQLException {
        return DriverManager.getConnection(connectionString, username, password);
    }

    public static Connection getConnection() throws SQLException {
        return createConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "zzzxxx111AAA");
    }

    public static SessionFactory sessionFactory = createSessionFactory();

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    private Util() {}

    public static SessionFactory createSessionFactory() {
        SessionFactory sessionFactory = null;
        try {
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(User.class);
            StandardServiceRegistryBuilder builder =
                    new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());

        } catch (Exception e) {
            System.out.println("Исключение!" + e);
        }
        return sessionFactory;
    }
}
