

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Statement;


@WebServlet("/ServletLoginForm")
public class ServletLoginForm extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	String loginusername=null, loginpassword=null;
	String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	String DB_URL = "jdbc:mysql://192.168.10.204:3306/pranjaldb";

	String Username = "pranjal";
	String Password = "pranjal@2019";

	Connection conn = null;
	java.sql.PreparedStatement stmt = null;
       java.sql.Statement statement;
	ServletLoginForm servletLogin;
	PrintWriter printWriter;
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String insertbutton=request.getParameter("insertbutton");
		String displayAllbutton=request.getParameter("displayAllbutton");
		String displaySpecificbutton=request.getParameter("displaySpecificbutton");
		String updateButton=request.getParameter("updatebutton");
		String deleteButton=request.getParameter("deletebutton");
		//System.out.println("Button Press is"+button+button2);
		printWriter = response.getWriter();
		response.setContentType("text/html");
		printWriter.print("<h2>Hello From Servlet</h2>");

		loginusername = request.getParameter("username");
		loginpassword = request.getParameter("password");
		

		printWriter.print("<h2>Username is </h2>"+loginusername);
		printWriter.print("<h2>Password is </h2>"+loginpassword);
		 
		servletLogin=new ServletLoginForm();
		if("insert".equals(insertbutton))
		{
			//System.out.println("Call to insert function");
			servletLogin.insertdata(loginusername,loginpassword,response);
			printWriter.print("<h3>Data Is Interted into database SuccessFully</h3>");
		}
		else if("displayAll".equals(displayAllbutton))
		{
			servletLogin.displayAllData(response);
			printWriter.print("<h3>Retrieved Data is.....</h3>");
		}
		else if("displaySpecific".equals(displaySpecificbutton))
		{
			servletLogin.displaySpecificData(loginusername, loginpassword, response);
			printWriter.print("<h3>Retrieved Data is.....</h3>");
		}
		else if("Update".equals(updateButton))
		{
			servletLogin.updateData(loginusername, loginpassword, response);
			printWriter.print("<h3>Data is Updated Successfully.....</h3>");
		}
		else if("delete".equals(deleteButton))
		{
			servletLogin.deleteData(loginusername, loginpassword, response);
			printWriter.print("<h3>Data is Deleted Successfully.....</h3>");
		}
		//servletLogin.updateData(loginusername, loginpassword, response);
		doGet(request, response);
	}
	public void insertdata(String user,String pass, HttpServletResponse response) throws IOException {
		printWriter=response.getWriter();
		int no=0 ;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, Username, Password);
		}
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

		}
		String sql = "INSERT INTO loginData VALUES(?,?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user);
			stmt.setString(2, pass );
			 no = stmt.executeUpdate();
			//printWriter.print("<h2>Inserted </h2>"+no);
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		/*
		 * if(no==1) { printWriter.print("<h2>Inserted data SuccessFully </h2>"); }
		 */
	}
	
	public void displaySpecificData(String user,String pass,HttpServletResponse response) throws IOException
	{
		printWriter=response.getWriter();
		try 
		{
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, Username, Password);
		}
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();

		}
		
		String sql3="SELECT * FROM loginData WHERE username='"+user+"'"; 
		//String sql3="SELECT * FROM loginData";
		try
		{
			stmt=stmt=conn.prepareStatement(sql3);
			ResultSet rs=stmt.executeQuery(sql3);
			while(rs.next()) 
			{ 
			  //String uname=rs.getString("username");
			 // String upass=rs.getString("password");
			  printWriter.print("<P ALIGN='center'><table BORDER=1>");
			  printWriter.print("<tr>");
			  printWriter.print("<th>"+Username+"</th>");
			  printWriter.print("<th>"+Password+"</th>");
			  printWriter.print("/tr");
			  
			  printWriter.print("<tr>");
			  	printWriter.print("<td>"+rs.getString("username")+"</td>");
			  	printWriter.print("<td>"+rs.getString("password")+"</td>");
			  printWriter.print("</tr>");
			  printWriter.print("</table>");
				/*
				 * printWriter.print("<table>" + "" + "<tr><th>Username</th>" +
				 * "<th>Password</th>" + "</tr>" +"<br>" + "<tr>" +
				 * "<td>+rs.getString("username");+</td>" + "<td>+upass</td>"
				 * 
				 * + "</tr>"
				 * 
				 * + "</table>"
				 * 
				 * );
				 */
			// printWriter.print("<p>Fetched Username: </p>"+uname); 
			// printWriter.print("<p>Fetched Password: </p>"+upass);
		  	}
		}
		catch(SQLException e)
		{ 
			e.printStackTrace();
		}
	
	}
	public void deleteData(String user, String pass, HttpServletResponse response) throws IOException
	{
		printWriter=response.getWriter();
		try 
		{
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, Username, Password);
		}
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();

		}
		String sql4="DELETE FROM loginData WHERE username='"+user+"'";
		try
		{
			statement=conn.createStatement();
			statement.executeUpdate(sql4);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void updateData(String user, String pass, HttpServletResponse response) throws IOException
	{
		printWriter=response.getWriter();
		try 
		{
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, Username, Password);
		}
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();

		}
		
		String sql5="UPDATE loginData SET username='Pranjal@1995' WHERE username='"+user+"'";
		try
		{
			statement=conn.createStatement();
			statement.executeUpdate(sql5);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		printWriter.print("<p>Data Updated Successfully</p>");
	
	//	servletLogin.displayData(loginusername,loginpassword,response);
	}
	public void displayAllData(HttpServletResponse response) throws IOException
	{
		printWriter=response.getWriter();
		try 
		{
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, Username, Password);
		}
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();

		}
		String sql3="SELECT * FROM loginData";
		try
		{
			stmt=conn.prepareStatement(sql3);
			ResultSet rs=stmt.executeQuery(sql3);
			while(rs.next()) 
			{ 
			  String uname=rs.getString("username");
			  String upass=rs.getString("password");
			 
			//printWriter.print("<p>Fetched Username: </p>"+uname); 
			// printWriter.print("<p>Fetched Password: </p>"+upass);
			  
			  printWriter.print("<P ALIGN='center'><table BORDER=1>");
			  printWriter.print("<tr>");
			  printWriter.print("<th>"+Username+"</th>");
			  printWriter.print("<th>"+Password+"</th>");
			  printWriter.print("/tr");
			  
			   printWriter.print("<tr>");
			  	printWriter.print("<td>"+rs.getString("username")+"</td>");
			  	printWriter.print("<td>"+rs.getString("password")+"</td>");
			  printWriter.print("</tr>");
			  printWriter.print("</table>");
		  	}
		}
		catch(SQLException e)
		{ 
			e.printStackTrace();
		}
		
	}
}
