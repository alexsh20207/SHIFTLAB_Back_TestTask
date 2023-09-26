package com.example.demoSHIFT.services;

import com.example.demoSHIFT.models.Interval;

import java.sql.*;

public class DataBase {
    private static String url = "jdbc:h2:C:\\Users\\alexs\\IdeaProjects\\demoSHIFT\\db\\intervalsDB";
    private static String user = "admin";
    private static String password = "admin";
    private static Connection connection;
    private static Statement statement;

    public static void connect() {
        try {
            Class.forName ("org.h2.Driver");
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Interval selectInterval(String query) {
        try {
            connect();
            System.out.println("Executing query: " + query);
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            Interval res = new Interval(resultSet.getString(2), resultSet.getString(3));

            close();
            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close() {
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void insert(Interval interval, String tableName, String specialSym) {
        connect();
        String query = "INSERT INTO " + tableName +
                "(START_INTERVAL, END_INTERVAL) values(" + specialSym +
                interval.getStart() + specialSym + ", " +
                specialSym + interval.getEnd() + specialSym +");";
        try {
            System.out.println("Executing query: " + query);
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        close();
    }
}
