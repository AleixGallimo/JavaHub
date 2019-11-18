package com.cs.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Author: JustinRolo
 * Date: 2019/11/6 11:56
 */

public class DBUtils {
    private static String className;
    private static String url;
    private static String username;
    private static String password;
    static{
        Properties properties = new Properties();
        try {
            properties.load(DBUtils.class.getClassLoader().getResourceAsStream("jdbc.properties"));
            className = properties.getProperty("className");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName(className);
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeAll(AutoCloseable ... closeables){
        for(AutoCloseable closeable : closeables){
            if(closeable != null){
                try {
                    closeable.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
