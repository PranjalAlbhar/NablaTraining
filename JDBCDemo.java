package practice;
import java.util.*;
import java.sql.*;
/*	1.execute : which returns boolean value when query is executed 
	2.executeQuery: which returns ResultSet Object which is used for retrieval purpose 
					Used for Select statement i.e DQL Statement 
	3.executeUpdate: which returns integer that row is affected by query like Insert Update Delete*/

public class JDBCDemo {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		System.out.println("Java Database Connectivity:");
		Scanner scanner=new Scanner(System.in);
		
		  String JDBC_DRIVER="com.mysql.jdbc.Driver"; 
		  String DB_URL="jdbc:mysql://192.168.10.204:3306/pranjaldb";
		  
		  String Username="pranjal";
		  String Password="pranjal@2019";
		  
		  Connection conn=null;
		  Statement stmt=null;
		  
		  
		  System.out.println("Connecting to database:"); 
		  try 
		  {
			  Class.forName(JDBC_DRIVER);
			  conn=DriverManager.getConnection(DB_URL,Username,Password);
			stmt=conn.createStatement();
			
			String sql="CREATE TABLE REGISTRATION " +
	                   "(id INTEGER not NULL, " +
	                   " first VARCHAR(255), " + 
	                   " last VARCHAR(255), " + 
	                   " age INTEGER, " + 
	                   " PRIMARY KEY ( id ))"; 
			
			String sql2="INSERT INTO Registration VALUES(11,'Komali','Khaladkar',10)";		//Adding data into table
			stmt.execute(sql2);
			
			String sql3="SELECT * FROM Registration ";								//Retrieving data From table
			ResultSet rs=stmt.executeQuery(sql3);
			
			while(rs.next())
			{
				int id=rs.getInt("id");
				String name=rs.getString("first");
				String lastname=rs.getString("last");
				int age=rs.getInt("age");
				System.out.println("id is "+id+" first name is "+name+" last name is "+lastname+" Age is "+age);		//Displaying data which we retrieve
			}
			
			String sql4="DELETE FROM Registration WHERE id=2";							//Removing data from database
			int ret= stmt.executeUpdate(sql4);
			System.out.println("rows affected by "+ret);
			
		  } 
		  catch(SQLException e) 
		  {
			  e.printStackTrace();
		  }
		//  System.out.println("Connection is done Successfully:");
		finally
		{
			stmt.close();															//Close the connection
			
		}
		  
		  PreparedStatement pstmt=conn.prepareStatement("INSERT INTO Registration values(? ? ?)" );
		  System.out.println("Enter the name:");
		  String first_name=scanner.next();
		  pstmt.setInt(1,56);
		  pstmt.setString(2,first_name );
		  pstmt.setString(3, "qwert");
		 pstmt.executeUpdate();

	}

}
