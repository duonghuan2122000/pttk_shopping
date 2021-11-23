/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom3.logicApplication.orderDAO;

import com.nhom3.model.order.Cart;

/**
 *
 * @author duong
 */
public interface CartDAO {
    Cart createCart();
    Cart getCart(int id);
    void updateCart(Cart cart);
}
