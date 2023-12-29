package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Queue;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.createNativeQuery("create table if not exists users(id bigserial primary key, age smallint, lastname varchar(255), name varchar(255));alter table users owner to postgres;", User.class).executeUpdate();
        tx1.commit();
        session.close();

    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.createNativeQuery("drop table if exists users;", User.class).executeUpdate();
        tx1.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(new User(name, lastName, age));
        tx1.commit();
        session.close();
        System.out.printf("User с именем – %s добавлен в базу данных", name);
        System.out.println();
    }


    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        User user = session.get(User.class, id);
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        tx1.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = (List<User>)  Util.getSessionFactory().openSession().createQuery("From User").list();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.createQuery("delete User").executeUpdate();
        tx1.commit();
        session.close();
    }
}
