/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pro.controller;

import com.pro.dao.DaoImpl;
import com.pro.model.*;
import com.pro.dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Akshay
 */
public class AddDisease extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
         String diseaseName= request.getParameter("txtdname");
        String symptoms= request.getParameter("txtsymptoms");
        String prevention= request.getParameter("txtpreventions");
        String cure= request.getParameter("txtcure");
        
            UserDAO u= new DaoImpl();
            System.out.println("dname: "+diseaseName+" Sym: "+symptoms+" pre: "+prevention+" cure: "+cure);
            Disease disease = new Disease("", symptoms, prevention, cure, diseaseName);
            
         if(u.addDisease(disease)){
                System.out.println("Disease Record Successfully Inserted");
                RequestDispatcher rd = request.getRequestDispatcher("regsuccess.jsp");
                request.setAttribute("auser", diseaseName.toLowerCase());
                
                rd.forward(request, response);
            }
            else
            {
                System.out.println("Failed to Add Disease Record");                
                out.println("Failed to add disease");
            }  
            
        
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
