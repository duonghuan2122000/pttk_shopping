/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom3.logicApplication.orderDAO;

import com.nhom3.logicApplication.bookDAO.BookDAO;
import com.nhom3.logicApplication.bookDAO.BookDAOImpl;
import com.nhom3.model.book.BookItem;
import com.nhom3.model.order.BoughtBookItem;
import com.nhom3.model.order.Cart;
import com.nhom3.utils.JdbcUtils;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author duong
 */
public class CartDAOImpl implements CartDAO{

    private JdbcUtils jdbcUtils;
    private BookDAO bookDAO;
    
    public CartDAOImpl(){
        try {
            jdbcUtils = new JdbcUtils();
            bookDAO = new BookDAOImpl();
        } catch (IOException ex) {
            Logger.getLogger(CartDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public Cart createCart() {
        Cart cart = new Cart();
        String sql = "INSERT INTO cart (TotalAmount, CreatedDate) VALUES (?, ?)";
        try(Connection connection = jdbcUtils.connect(); PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            statement.setDouble(1, cart.getTotalAmount());
            statement.setDate(2, new java.sql.Date(System.currentTimeMillis()));
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next()){
                cart.setId((int) rs.getLong(1));
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return cart;
    }

    @Override
    public Cart getCart(int id) {
        Cart cart = null;
        String sqlCart = "SELECT * FROM cart WHERE Id = ? LIMIT 1";
        try(Connection connection = jdbcUtils.connect(); PreparedStatement statment = connection.prepareStatement(sqlCart)){
            statment.setInt(1, id);
            ResultSet rs = statment.executeQuery();
            if(rs.next()){
                double totalAmount = rs.getDouble("TotalAmount");
                Date createdDate = rs.getDate("CreatedDate");
                cart = new Cart(id, totalAmount, createdDate);
                List<BoughtBookItem> bookItems = getBookCart(id);
                cart.setBooks(bookItems);
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return cart;
    }
    
    private List<BoughtBookItem> getBookCart(int cartId){
        List<BoughtBookItem> items = new ArrayList<>();
        String sqlBookCart = "SELECT * FROM boughtBookItem WHERE CartId = ?";
        try(Connection connection = jdbcUtils.connect(); PreparedStatement statement = connection.prepareStatement(sqlBookCart)){
            statement.setInt(1, cartId);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                int id = rs.getInt("Id");
                int quantity = rs.getInt("Quantity");
                BookItem bookItem = bookDAO.get(rs.getString("BookBarCode"));
                BoughtBookItem item = new BoughtBookItem(id, quantity, bookItem);
                items.add(item);
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return items;
    }

    @Override
    public void updateCart(Cart cart) {
        String sql = "UPDATE cart SET TotalAmount = ? WHERE Id = ?";
        try(Connection connection = jdbcUtils.connect(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setDouble(1, cart.getTotalAmount());
            statement.setInt(2, cart.getId());
            statement.executeUpdate();
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
}
