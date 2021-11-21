/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom3.model.user;

import java.util.Date;

/**
 *
 * @author duong
 */
public class Employee {
    private int id;
    private String role;
    private Date createdDate;
    private FullName fullName;
    private Account account;
    private Address address;

    public Employee(int id, String role, Date createdDate, FullName fullName, Account account, Address address) {
        this.id = id;
        this.role = role;
        this.createdDate = createdDate;
        this.fullName = fullName;
        this.account = account;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public FullName getFullName() {
        return fullName;
    }

    public void setFullName(FullName fullName) {
        this.fullName = fullName;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
    
}
