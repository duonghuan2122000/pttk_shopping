/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom3.presentation;

import com.google.gson.Gson;
import com.nhom3.logicApplication.user.EmployeeDAO;
import com.nhom3.logicApplication.user.EmployeeDAOImpl;
import com.nhom3.model.user.Employee;
import java.io.IOException;
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
@WebServlet(name = "EmployeeController", urlPatterns = {"/admin/login", "/admin/postLogin"})
public class EmployeeController extends HttpServlet {
    
    private EmployeeDAO employeeDAO;
    
    public EmployeeController(){
        employeeDAO = new EmployeeDAOImpl();
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
            switch(action){
                case "/admin/login":
                    showLoginForm(request, response);
                    break;
                case "/admin/postLogin":
                    verify(request, response);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    private void showLoginForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/admin/login.jsp");
        dispatcher.forward(request, response);
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

    private void verify(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        
        Employee employee = employeeDAO.login(username, password);
        
        if(employee == null){
            response.sendRedirect("/Shopping/admin/login");
        } else {
            Gson gson = new Gson();
            String employeeStr = gson.toJson(employee);
            addCookie(response, "employee", employeeStr);
            response.sendRedirect("/Shopping");
        }
    }
    
    private void addCookie(HttpServletResponse response, String key, String value) {
        Cookie cookie = new Cookie(key, value);
        response.addCookie(cookie);
    }

}
