package register;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import register.Cart;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ayushsingh
 */
@WebServlet(urlPatterns = {"/deleteItem"})
public class deleteItem extends HttpServlet {

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //String name = request.getParameter("name");
        int key = Integer.parseInt(request.getParameter("key"));
        HttpSession session = request.getSession();
        ArrayList mycart = (ArrayList) session.getAttribute("itemlist");
        mycart.remove(key);
        session.setAttribute("itemlist", mycart);
               
         response.sendRedirect("checkout.jsp");
        
    }

}
