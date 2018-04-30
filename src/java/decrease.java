/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import register.item;

/**
 *
 * @author ayushsingh
 */
@WebServlet(urlPatterns = {"/decrease"})
public class decrease extends HttpServlet {

    

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         HttpSession session = request.getSession();
        int i = Integer.parseInt(request.getParameter("itemoffset"));
        ArrayList mycart = (ArrayList) session.getAttribute("itemlist");
        item it = (item) mycart.get(i);
        int quan = it.getQuant();
        mycart.remove(i);
        quan--;
        item myitem = new item(it.name, it.price, quan, it.itemid);
        mycart.add(myitem);
        response.sendRedirect("checkout.jsp");
        
       
    }

   
}
