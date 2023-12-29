package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String createQuery = "create table if not exists users(id bigserial primary key, age smallint, lastname varchar(255), name varchar(255));alter table users owner to postgres;";
        try {
            Statement statement = Util.getConnection().createStatement();
            statement.executeUpdate(createQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        String dropQuery = "drop table if exists users;";
        try {
            Statement statement = Util.getConnection().createStatement();
            statement.executeUpdate(dropQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String insertQuery = "INSERT INTO users(age, lastname, name) VALUES (?, ?, ?)";
        try (PreparedStatement statement = Util.getConnection().prepareStatement(insertQuery)) {
            statement.setByte(1, age);
            statement.setString(2, lastName);
            statement.setString(3, name);
            statement.executeUpdate();
            System.out.printf("User с именем – %s добавлен в базу данных", name);
            System.out.println();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String removeQuery = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement statement = Util.getConnection().prepareStatement(removeQuery)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<User> getAllUsers() {
        Statement statement = null;
        ArrayList<User> list = new ArrayList<User>();

        try {
            statement = Util.getConnection().createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM users");
            while (results.next()) {
                User user = new User();
                user.setId(results.getLong(1));
                user.setAge((byte)results.getInt(2));
                user.setLastName(results.getString(3));
                user.setName(results.getString(4));
                list.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public void cleanUsersTable() {
        String dropQuery = "delete from users";
        try {
            Statement statement = Util.getConnection().createStatement();
            statement.executeUpdate(dropQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
