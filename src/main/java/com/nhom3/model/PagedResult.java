/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom3.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author duong
 */
public class PagedResult<T> {
    private int totalRecord;
    private int totalPages;
    private List<T> items; 
    
    public PagedResult(){
        totalPages = 0;
        totalRecord = 0;
        items = new ArrayList<>();
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
    
    
}
