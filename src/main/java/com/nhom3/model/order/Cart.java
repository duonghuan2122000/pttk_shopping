/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom3.model.order;

import java.util.Date;
import java.util.List;

/**
 *
 * @author duong
 */
public class Cart {
    private int id;
    private double totalAmount;
    private Date createdDate;
    private List<BoughtBookItem> books;

    public Cart(int id, double totalAmount, Date createdDate) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.createdDate = createdDate;
    }
    
    public Cart(){
        this.totalAmount = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public List<BoughtBookItem> getBooks() {
        return books;
    }

    public void setBooks(List<BoughtBookItem> books) {
        this.books = books;
    }
    
    
}
