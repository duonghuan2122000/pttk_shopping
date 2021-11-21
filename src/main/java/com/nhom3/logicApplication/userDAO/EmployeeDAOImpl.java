/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom3.logicApplication.userDAO;

import com.nhom3.model.user.Account;
import com.nhom3.model.user.Address;
import com.nhom3.model.user.Employee;
import com.nhom3.model.user.FullName;
import com.nhom3.utils.JdbcUtils;
import com.nhom3.utils.SecureUtils;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author duong
 */
public class EmployeeDAOImpl implements EmployeeDAO {

    private JdbcUtils jdbcUtils;

    public EmployeeDAOImpl() {
        try {
            jdbcUtils = new JdbcUtils();
        } catch (IOException ex) {
            Logger.getLogger(EmployeeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Hàm thực hiện đăng nhập
     *
     * @param username
     * @param password
     * @return dbhuan 21/11/2021
     */
    public Employee login(String username, String password) {
        Employee employee = null;
        String sqlAccount = "SELECT * FROM account WHERE username = ? AND password = ?";

        try (Connection connection = jdbcUtils.connect(); PreparedStatement statement = connection.prepareStatement(sqlAccount)) {
            statement.setString(1, username);
            statement.setString(2, SecureUtils.md5(password));
            
            System.out.println(sqlAccount);
            
            ResultSet rs = statement.executeQuery();
            
            if(rs.next()){
                int id = rs.getInt("Id");
                Account account = new Account(id, username);
                employee = getByAccount(account);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return employee;
    }
    
    private Employee getByAccount(Account account){
        Employee employee = null;
        String sqlEmployee = "SELECT * FROM employee WHERE AccountId = ? LIMIT 1";
        try(Connection connection = jdbcUtils.connect(); PreparedStatement statement = connection.prepareCall(sqlEmployee)){
            statement.setInt(1, account.getId());
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                int id = rs.getInt("Id");
                String role = rs.getString("Role");
                Date createdDate = rs.getDate("CreatedDate");
                FullName fullName = getFullNameById(rs.getInt("FullNameId"));
                Address address = getAddressById(rs.getInt("AddressId"));
                employee = new Employee(id, role, createdDate, fullName, account, address);
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return employee;
    }
    
    private FullName getFullNameById(int id){
        FullName fullName = null;
        String sqlFullName = "SELECT * FROM fullName WHERE Id = ? LIMIT 1";
        try(Connection connection = jdbcUtils.connect(); PreparedStatement statement = connection.prepareStatement(sqlFullName)){
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                fullName = new FullName(id, firstName, lastName);
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return fullName;
    }
    
    private Address getAddressById(int id){
        Address address = null;
        String sqlAddress = "SELECT * FROM address WHERE Id = ? LIMIT 1";
        try(Connection connection = jdbcUtils.connect(); PreparedStatement statement = connection.prepareStatement(sqlAddress)){
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                String houseNumber = rs.getString("HouseNumber");
                String street = rs.getString("Street");
                String district = rs.getString("District");
                String city = rs.getString("City");
                address = new Address(id, houseNumber, street, district, city);
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return address;
    }
}
