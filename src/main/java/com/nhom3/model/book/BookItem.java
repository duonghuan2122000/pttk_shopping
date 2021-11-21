/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom3.model.book;

/**
 *
 * @author duong
 */
public class BookItem {
    private String barCode;
    private double price;
    private double discount;
    private Book book;

    public BookItem(String barCode, double price, double discount, Book book) {
        this.barCode = barCode;
        this.price = price;
        this.discount = discount;
        this.book = book;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
    
    
   
}
