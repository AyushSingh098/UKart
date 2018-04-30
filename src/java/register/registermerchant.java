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

/**
 *
 * @author ayushsingh
 */
@WebServlet(name = "registermerchant", urlPatterns = {"/registermerchant"})
public class registermerchant extends HttpServlet {

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String mfname = request.getParameter("mfname");
        String mlname = request.getParameter("mlname");
        String mmobno = request.getParameter("mmobno");
        String mstore = request.getParameter("mstore");
        String madd = request.getParameter("maddress");
        String memail = request.getParameter("memail");
        String mpass = request.getParameter("mpass");
        String mconpass = request.getParameter("mconpass");
        if(!mpass.equals(mconpass))
        {
            out.print("Password and Confirm Password do match, Hit back and try again!");
        }
        
        String query="SELECT * FROM merchantdata Where Email = '"+memail.trim()+"'";
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
        rs = stmt.executeQuery(query);
        rs.next();
        if(memail.equals(rs.getString("Email")))
        {
            response.sendRedirect("merchantlogin.jsp");
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
        String mainquery="INSERT INTO merchantdata (FirstName, LastName, Store, Mobile, Address, Email, Password)" + "values(?,?,?,?,?,?,?)";
        //String query="INSERT INTO `` (FirstName, LastName, Mobile, College, Hostel, Email, Password)" + "values(?,?,?,?,?,?,?)";
        
        PreparedStatement pst= (PreparedStatement)conn.prepareStatement(mainquery);
        pst.setString(1, mfname);
        pst.setString(2, mlname);
        pst.setString(3, mstore);
        pst.setString(4, mmobno);
        pst.setString(5, madd);
        pst.setString(6, memail);
        pst.setString(7, mpass);
        int i=pst.executeUpdate();
        if(i>0)
        {
            
            response.sendRedirect("merchantlogin.jsp");
            
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
