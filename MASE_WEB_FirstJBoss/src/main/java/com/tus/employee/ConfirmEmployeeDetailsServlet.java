package com.tus.employee;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ConfirmEmployeeDetailsServlet
 */
@WebServlet("/ConfirmEmployeeDetailsServlet")
public class ConfirmEmployeeDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmEmployeeDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String salary = request.getParameter("salary");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<body>");
		
		if (name.equals("")) {
			out.println("<p>No name specified.</p>");
			if (age.equals("")) {
				out.println("<p>No age specified.</p>");
				if (salary.equals("")) {
					out.println("<p>No salary specified.</p>");
				}
			}
		} else if (age.equals("")) {
				out.println("<p>No age specified.</p>");
				if (salary.equals("")) {
					out.println("<p>No salary specified.</p>");
				} else {
					if (salary.equals("")) {
						out.println("<p>No salary specified.</p>");
					}
				} 
		} else if (salary.equals("")) {
			out.println("<p>No salary specified.</p>");
		} else {
			out.println("<h1>Hello " + name + ".</h1>");
			out.println("<h1>You are " + age + " years old, and your salary is " + salary + " euro.</h1>");
		}	
		
		out.println("</body>");
		out.println("</html>");
		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
