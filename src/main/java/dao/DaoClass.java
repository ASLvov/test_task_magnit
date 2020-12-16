package main.java.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoClass {
    private static final String CREATE_TABLE = "DROP TABLE IF EXISTS test; \n" +
            "CREATE TABLE test (field integer not null)";
    private static final String INSERT_NUMBERS = "INSERT INTO test (field) VALUES (?)";
    private static final String SELECT_NUMBERS = "SELECT * FROM test";

    private String url;
    private String login;
    private String password;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // создание пустой базы данных
    private void createDB() throws SQLException {
        try {
            Connection con = DriverManager.getConnection(url, login, password);
            Statement stmt = con.createStatement();
            stmt.executeUpdate(CREATE_TABLE);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //заполнение базы данных значениями от 1 до N
    public void insertNumbers(long N) throws SQLException {
        createDB();
        try (Connection con = DriverManager.getConnection(url, login, password);
             PreparedStatement stmt = con.prepareStatement(INSERT_NUMBERS)) {
            for (int i = 1; i <= N; i++) {
                stmt.setLong(1, i);
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

    // считывание значений базы данных в ArrayList
    public List<Integer> getNumbers() throws SQLException {
        List<Integer> numbers = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(url, login, password);
             PreparedStatement stmt = con.prepareStatement(SELECT_NUMBERS)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int n = rs.getInt("field");
                numbers.add(n);
            }
        }
        return numbers;
    }

}
