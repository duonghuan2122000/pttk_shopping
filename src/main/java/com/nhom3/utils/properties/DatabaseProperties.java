/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom3.utils.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author duong
 */
public class DatabaseProperties {

    private Properties properties;

    public DatabaseProperties() throws FileNotFoundException, IOException {
        properties = new Properties();
        System.out.println(getClass().getClassLoader().getResourceAsStream("database.properties"));
        properties.load(getClass().getClassLoader().getResourceAsStream("database.properties"));
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
