/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom3.presentation;

import com.nhom3.logicApplication.bookDAO.BookDAO;
import com.nhom3.logicApplication.bookDAO.BookDAOImpl;
import com.nhom3.logicApplication.orderDAO.CartDAO;
import com.nhom3.logicApplication.orderDAO.CartDAOImpl;
import com.nhom3.model.PagedResult;
import com.nhom3.model.book.BookItem;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author duong
 */
@WebServlet(name = "BookController", urlPatterns = {"/books", "/book/addToCart"})
public class BookController extends HttpServlet {

    private BookDAO bookDAO;
    private CartDAO cartDAO;

    public BookController() {
        bookDAO = new BookDAOImpl();
        cartDAO = new CartDAOImpl();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/books":
                    showBookList(request, response);
                    break;
                case "/book/addToCart":
                    addToCart(request, response);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void showBookList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("search") != null ? request.getParameter("search").trim() : "";
        int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
        int pageSize = request.getParameter("pageSize") != null ? Integer.parseInt(request.getParameter("pageSize")) : 10;
        PagedResult<BookItem> bookPag = bookDAO.getList(search, page, pageSize);
        System.out.println(bookPag.getTotalRecord());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/book/list.jsp");
        request.setAttribute("bookPag", bookPag);
        dispatcher.forward(request, response);
    }

    private void addToCart(HttpServletRequest request, HttpServletResponse response) {
        int cartId = 0;
        
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(int i = 0; i < cookies.length; i++){
                Cookie cookie = cookies[i];
                if(cookie.getName().equalsIgnoreCase("cartId")){
                    cartId = Integer.parseInt(cookie.getValue());
                    break;
                }
            }
        }
        
        if(cartId == 0){
            cartId = cartDAO.createCart();
        }
        String barCode = request.getParameter("barCode");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        bookDAO.addToCart(barCode, quantity, cartId);
    }

}
