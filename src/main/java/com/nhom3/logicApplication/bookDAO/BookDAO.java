/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom3.logicApplication.bookDAO;

import com.nhom3.model.PagedResult;
import com.nhom3.model.book.BookItem;
import java.util.List;

/**
 *
 * @author duong
 */
public interface BookDAO {
    PagedResult<BookItem> getList(String search, int page, int limit);
    BookItem get(String barCode);
    void addToCart(String barCode, int cartId, int quantity);
}
