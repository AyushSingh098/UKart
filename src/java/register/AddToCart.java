package register;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ayushsingh
 */
public class AddToCart extends HttpServlet {

   
    String Add;
    String mob;
    String coll;
    static int i=1;
    int qua;
    int quantity[];
     protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("uemail");
        
        PrintWriter out = response.getWriter();
        int user_id=0;
        
        int item_id = 0;
        if(session.getAttribute("user")==null)
        {
            response.sendRedirect("login.jsp");
        }
        else
        {
            
            ArrayList mycart = (ArrayList) session.getAttribute("itemlist");
        
                String name = request.getParameter("item_name");
                Double price = Double.parseDouble(request.getParameter("item_price"));
                int qua = Integer.parseInt(request.getParameter("item_quantity"));
                int iid = Integer.parseInt(request.getParameter("item_id"));
                 item myitem = new item(name, price, qua, iid);
                 mycart.add(myitem);
                session.setAttribute("itemlist", mycart);

                response.sendRedirect("checkout.jsp");
    
    }

   

}
}