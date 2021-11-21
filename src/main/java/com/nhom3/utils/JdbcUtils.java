/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom3.utils;

import com.nhom3.utils.properties.DatabaseProperties;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author duong
 */
public class JdbcUtils {

    private DatabaseProperties databaseproperties;

    /**
     * Constructor for class JdbcUtils.
     *
     * @Description: .
     * @author: DangBlack
     * @create_date: May 26, 2020
     * @version: 1.0
     * @modifer: DangBlack
     * @modifer_date: May 26, 2020
     * @throws IOException
     */
    public JdbcUtils() throws IOException {
        databaseproperties = new DatabaseProperties();
    }
    
    public Connection connect()
            throws FileNotFoundException, IOException, ClassNotFoundException, SQLException, SQLException {

        String url = databaseproperties.getProperty("url");
        String username = databaseproperties.getProperty("username");
        String password = databaseproperties.getProperty("password");

        // Step 1: register the driver class with DriverManager
        Class.forName(databaseproperties.getProperty("driver"));

        // Step 2: get a Database Connection
        Connection connection = DriverManager.getConnection(url, username, password);

        return connection;
    }
}
