package register;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static java.awt.SystemColor.window;
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
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author ayushsingh
 */
@WebServlet(urlPatterns = {"/login"})
public class login extends HttpServlet {

    

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String email = request.getParameter("loginemail");
        String pass = request.getParameter("loginpass");
        
        try
        {
        
        //loading drivers for mysql
        Class.forName("com.mysql.jdbc.Driver");

	//creating connection with the database 
        Connection con=DriverManager.getConnection
                     ("jdbc:mysql://localhost:3306/ukart","root","");
        Statement stmt = null;
        ResultSet rs = null;
        String query ="select *from userdata where Email = '"+ email.trim() + "'";
        stmt = con.createStatement();
        rs = stmt.executeQuery(query);
        if (!rs.isBeforeFirst() ) {    
            response.sendRedirect("registered.jsp");
} 
        rs.next();
        if(pass.equals(rs.getString("Password")))
        {
            ArrayList mycart = new ArrayList();
            int totalcost = 0;
            HttpSession session = request.getSession(true);
            session.setAttribute("user", rs.getString("FirstName"));
            session.setAttribute("uemail", rs.getString("Email"));
            session.setAttribute("itemlist", mycart);
            session.setAttribute("total", totalcost);
            response.sendRedirect("index.jsp");
															
								
        }
        else
        {   
                //error in case of wrong password
                response.sendRedirect("login.jsp");
        }
        stmt.close();
        rs.close();
        con.close();
        }
        
        catch(Exception se)
        {
            se.printStackTrace();
        }
        
        
    }

}
