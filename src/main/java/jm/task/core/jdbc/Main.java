package jm.task.core.jdbc;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceHibernateImpl;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {


//        Создание таблицы User(ов)
//        Добавление 4 User(ов) в таблицу с данными на свой выбор.
//                После каждого добавления должен быть вывод в консоль ( User с именем – name добавлен в базу данных )
//        Получение всех User из базы и вывод в консоль ( должен быть переопределен toString в классе User)
//        Очистка таблицы User(ов)
//        Удаление таблицы

        UserServiceHibernateImpl hiberImpl = new UserServiceHibernateImpl();
        hiberImpl.createUsersTable();
        hiberImpl.saveUser("Иван", "Иванов", (byte) 34);
        hiberImpl.saveUser("Петр", "Петров", (byte) 35);
        hiberImpl.saveUser("Василий", "Васильев", (byte) 35);
        hiberImpl.saveUser("Семен", "Семенов", (byte) 35);
        hiberImpl.removeUserById(1);
        List<User> hiberUsers = hiberImpl.getAllUsers();
        System.out.println();
        for (User user : hiberUsers) {
            System.out.println(user);
        }
        hiberImpl.cleanUsersTable();
        hiberImpl.dropUsersTable();

        System.out.println();
        UserServiceImpl jdbcImpl = new UserServiceImpl();
        jdbcImpl.createUsersTable();
        jdbcImpl.saveUser("Петр", "Иванов", (byte) 34);
        jdbcImpl.saveUser("Семен", "Петров", (byte) 35);
        jdbcImpl.saveUser("Иван", "Васильев", (byte) 35);
        jdbcImpl.saveUser("Василий", "Семенов", (byte) 35);
        jdbcImpl.removeUserById(4);
        System.out.println();
        List<User> jdbcUsers = jdbcImpl.getAllUsers();
        for (User user : jdbcUsers) {
            System.out.println(user);
        }
        jdbcImpl.cleanUsersTable();
        jdbcImpl.dropUsersTable();

    }
}
