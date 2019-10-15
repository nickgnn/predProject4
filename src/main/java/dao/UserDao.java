package dao;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    void createTable() throws SQLException;

    void dropTable() throws SQLException;

    List<User> getAllUsers() throws SQLException;

    void addUser(String name, int age) throws SQLException;

    User getUserByName(String name) throws SQLException;

    int updateUser(User user, String name) throws SQLException;

    int updateUser(User user, int age) throws SQLException;

    int updateUser(User user, Long ID) throws SQLException;

    long getUserIdByName(String name) throws SQLException;

    void deleteUserByName(String name) throws SQLException;

    void deleteUserById(Long id) throws SQLException;
}
