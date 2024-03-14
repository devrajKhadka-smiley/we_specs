package register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/RegisterServlet" })
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter printOut = response.getWriter();
		response.setContentType("text/html");
		printOut.print("");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		response.setContentType("text/html");
		PrintWriter printOut = response.getWriter();
		
		String firstname = request.getParameter("firstName");
		String lastname = request.getParameter("lastName");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String phoneno = request.getParameter("phoneNumber");
		String password = request.getParameter("password");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url  = "jdbc:mysql://localhost:3306/week2_lab";
			String user = "root";
			String pass = "";
			Connection con = DriverManager.getConnection(url, user, pass);
			
			String query = "Insert into test_table (firstname, lastname, username, email, phoneno, password) value(?,?,?,?,?,?)";
			PreparedStatement st = con.prepareStatement(query);

			st.setString(1, firstname);
			st.setString(2, lastname);
			st.setString(3, username);
			st.setString(4, email);
			st.setString(5, phoneno);
			st.setString(6, password);
			
			int result = st.executeUpdate();
			if(result>0) {
				printOut.println("<h1>Your account is registered successfully</h1>");
			}
			
		} catch (SQLException | ClassNotFoundException ex) {
			// TODO: handle exception
			printOut.println("<h1>Please Enter the correct data!</h1>");

		}
	}

}
