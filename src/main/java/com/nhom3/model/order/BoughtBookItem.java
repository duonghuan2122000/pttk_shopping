/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom3.model.order;

import com.nhom3.model.book.BookItem;

/**
 *
 * @author duong
 */
public class BoughtBookItem {
    private int id;
    private int quantity;
    private BookItem bookItem;

    public BoughtBookItem(int id, int quantity, BookItem bookItem) {
        this.id = id;
        this.quantity = quantity;
        this.bookItem = bookItem;
    }
    
    public BoughtBookItem(){
        this.id = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BookItem getBookItem() {
        return bookItem;
    }

    public void setBookItem(BookItem bookItem) {
        this.bookItem = bookItem;
    }
    
    
    
}
