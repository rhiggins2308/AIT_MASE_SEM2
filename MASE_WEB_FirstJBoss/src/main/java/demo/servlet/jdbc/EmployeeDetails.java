package demo.servlet.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/EmployeeDetails")
public class EmployeeDetails extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private String url;
	private String driver = null;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			url = "jdbc:mysql://localhost/employeedb?serverTimezone=GMT";
			driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			
			String user = "root", pwd = "@*data_rep";
			//String user = "root", pwd = "root";
			Connection connection = DriverManager.getConnection(url, user, pwd);
			System.out.println("Some status about the connection : " + connection.isClosed());
			
			String sql = "SELECT * FROM employees ORDER BY name";
			Statement statement = connection.createStatement();
			ResultSet resultset = statement.executeQuery(sql);
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<table><tr><td>Name</td><td>Salary</td></tr>");
			while (resultset.next()) {
				out.println("<tr><td>" + resultset.getString("name")
						+ "</td><td>" + resultset.getString("salary")
						+ "</td></tr>");
			}
			out.println("</table>");
			// Clean-up environment
			resultset.close();
			statement.close();
			connection.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			System.out.println("SQL Exception Caught");
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			System.out.println("Some Other Exception Caught");
			e.printStackTrace();
		}
	} // end try

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
