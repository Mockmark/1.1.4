package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Leon", "Kennedy", (byte) 36);
        userService.saveUser("Steve", "Jordan", (byte) 43);
        userService.saveUser("Дмитрий", "Алексин", (byte) 55);
        userService.saveUser("Jack", "Brown", (byte) 23);

        List<User> userlist = userService.getAllUsers();
        for (User user : userlist) {
            System.out.println(user.toString());
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
