package com.company.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.jdbcconnection.DBConnection;

/**
 * Servlet implementation class ProductListServlet
 */
@WebServlet("/searchProduct")
public class SearchProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String dbUrl = "jdbc:mysql://localhost:3306/eproduct";
		String username = "root";
		String password = "password";
		
		response.setContentType ("text/html");
        PrintWriter out = response.getWriter ();
        
        RequestDispatcher rd = null;
        
  		int id = 0;
        
        out.println("<html><body>");
        
        try {
        	
            id = Integer.parseInt(request.getParameter ("productId"));
     
        	DBConnection dbConnection = new DBConnection(dbUrl, username, password);
			
        	PreparedStatement stmnt = dbConnection.getConnection().prepareStatement ("select * from product where id=?");
            
        	stmnt.setString (1, request.getParameter ("productId"));
        	
        	ResultSet rs = stmnt.executeQuery();
        	
        	System.out.println(stmnt);
        	
        	out.println("<div align='center'>");
        	
        	out.println("<h1> PRODUCT DETAILS</h1>");
        	
        	out.println("<style> table,td,th {border:1px solid green; padding:10px;}</style>");
        	
            out.print("<table>");
      
            out.print("<th> Product ID </th>");
    		out.print("<th> Product Name </th>");
    		out.print("<th> Product Price </th>");
    		out.print("<th> Data Of Product Added </th>");
            
            out.print ("</tr>");
            
            /* Printing result */
            while (rs.next()){
            	
             out.print("<tr><td>" 
            		 	+ rs.getInt("Id") + "</td><td>" 
            		 	+ rs.getString("name") + " </td><td>" 
            		 	+ rs.getInt("price") + " </td><td>" 
            		 	+ rs.getDate("date_added") + "</td></tr>");
            }
            
            out.print ("</table>");
        	
        }catch(NumberFormatException e){
			
			rd = request.getRequestDispatcher("index.html");
			rd.include(request, response);
			out.println("<div align='center'>");
			out.print("<span style='color: red; margin-left: 25px'>Invalid product ID. Please Enter a valid numeric ID</span><br/>");
			out.println("</div>");
			
		}catch (Exception e2){
            e2.printStackTrace ();
            
        }finally{
            out.close ();
        }	
        
        out.println("</div>");
        out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
