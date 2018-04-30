/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package register;

import java.sql.PreparedStatement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
@WebServlet(name = "registerukart", urlPatterns = {"/registerukart"})
public class registerukart extends HttpServlet {

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String collname = request.getParameter("collname");
        String mobno = request.getParameter("mobno");
        String hosadd = request.getParameter("hosadd");
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        String conpass = request.getParameter("conpass");
        if(!pass.equals(conpass))
        {
            out.print("Password and Confirm Password do match, Hit back and try again!");
        }
        
        String query="SELECT * FROM userdata Where Email = '"+email.trim()+"'";
        Connection  con= null;
        PreparedStatement ps=null;
        try
        {
        
        //loading drivers for mysql
        Class.forName("com.mysql.jdbc.Driver");

	//creating connection with the database 
        con=DriverManager.getConnection
                     ("jdbc:mysql://localhost:3306/ukart","root","");
        Statement stmt = null;
        ResultSet rs = null;
        stmt = con.createStatement();
        HttpSession session=request.getSession();
        rs = stmt.executeQuery(query);
        rs.next();
        if(email.equals(rs.getString("email")))
        {   //session.setAttribute("nid",rs.getInt(1));
            //out.print(rs.getInt(1));
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
        try
        {
        
        //loading drivers for mysql
        Class.forName("com.mysql.jdbc.Driver");

	//creating connection with the database 
        Connection conn=DriverManager.getConnection
                     ("jdbc:mysql://localhost:3306/ukart","root","");
        String mainquery="INSERT INTO userdata (FirstName, LastName, Mobile, College, Hostel, Email, Password)" + "values(?,?,?,?,?,?,?)";
        
        PreparedStatement pst= (PreparedStatement)conn.prepareStatement(mainquery);
        pst.setString(1, fname);
        pst.setString(2, lname);
        pst.setString(3, mobno);
        pst.setString(4, collname);
        pst.setString(5, hosadd);
        pst.setString(6, email);
        pst.setString(7, pass);
        int i=pst.executeUpdate();
        if(i>0)
        {
            //out.println("Registered");
            response.sendRedirect("login.jsp");
            
        }
        
        pst.close();
        conn.close();
    }
        
        catch(Exception se)
        {
            se.printStackTrace();
        }
    }
}
