package lesson.jdbc1;

import java.io.IOException;
import java.io.InputStream;
//import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.Properties;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JDBCDemoNew
 */
@WebServlet("/JDBCDemoNew")
public class JDBCDemoNew extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JDBCDemoNew() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		try {
			PrintWriter out = response.getWriter();
			out.println("<html><body>");
//			InputStream fis = getServletContext().getResourceAsStream("/WEB-INF/Config.Properties");
//			//File input stream
//			Properties props = new Properties();
//			props.load(fis);
			
			String url = "jdbc:mysql://localhost:3306/assignment3";
			String username = "root";
			String password = "root";
			
			JDBCConnection con;
			con = new JDBCConnection(url, username, password);
//			con = new JDBCConnection(props.getProperty("url"), props.getProperty("username"), props.getProperty("password"));
			out.println("JDBC connection established<br>");
			
			Statement statement = con.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate("insert into product values('Iron', '20', 'Heavy')");
			
//			statement.executeUpdate("delete from product where Type = 'Light'");
//			statement.executeUpdate("create database tempDB");
			
//			CallableStatement spStatement = con.getConnection().prepareCall("call add_user('Gold', '50', 'Heavy')");
//			spStatement.executeUpdate();
			
			ResultSet result = statement.executeQuery("select * from product");
			while(result.next()) {
				out.println(result.getString("Item") + " - " + result.getString("Quantity") + " - " + result.getString("Type"));
			}
			
			con.closConnection();
			out.println("Connection is closed");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
