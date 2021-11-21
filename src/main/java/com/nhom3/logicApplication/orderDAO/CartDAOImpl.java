/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom3.logicApplication.orderDAO;

import com.nhom3.utils.JdbcUtils;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author duong
 */
public class CartDAOImpl implements CartDAO{

    private JdbcUtils jdbcUtils;
    
    public CartDAOImpl(){
        try {
            jdbcUtils = new JdbcUtils();
        } catch (IOException ex) {
            Logger.getLogger(CartDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public int createCart() {
        int cartId = 0;
        String sql = "INSERT INTO cart (TotalAmount, CreatedDate) VALUES (?, ?)";
        try(Connection connection = jdbcUtils.connect(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setDouble(1, 0);
            statement.setDate(2, new java.sql.Date(System.currentTimeMillis()));
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next()){
                cartId = (int) rs.getLong(1);
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return cartId;
    }
    
}
