/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom3.logicApplication.userDAO;

import com.nhom3.model.user.Employee;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author duong
 */
public interface EmployeeDAO {
    /**
     * Hàm thực hiện đăng nhập      
     * @param username
     * @param password
     * @return 
     * dbhuan 21/11/2021
     */
    public Employee login(String username, String password);
}
