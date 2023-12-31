
package jm.task.core.jdbc.service;

        import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
        import jm.task.core.jdbc.model.User;

        import java.util.List;

public class UserServiceHibernateImpl implements UserService {

    private UserDaoHibernateImpl usersDao = new UserDaoHibernateImpl();
    public void createUsersTable() {
        usersDao.createUsersTable();
    }

    public void dropUsersTable() {
        usersDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        usersDao.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        usersDao.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return usersDao.getAllUsers();
    }

    public void cleanUsersTable() {
        usersDao.cleanUsersTable();
    }
}



