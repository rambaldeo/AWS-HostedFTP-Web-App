package planning.webapp.servlet;

import java.io.IOException;
import java.util.regex.Pattern;

import com.mongodb.client.MongoDatabase;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import planning.webapp.UserAccounts;
import planning.webapp.conn.MongoDBUtils;
import planning.webapp.encryption.Encrypt;
import planning.webapp.utils.MyUtils;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet(urlPatterns = { "/SignUpServlet" })
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final Pattern TextPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$");
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignUpServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/SignUp.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String verifyPassword = request.getParameter("verifyPassword");

		UserAccounts user = null;
		boolean hasError = false;
		String errorString = null;
		boolean validPassword = false;

		if (firstName == null || lastName == null || userName == null || password == null) {
			hasError = true;
			errorString = "Require all fields to be filled";
		} else {
				validPassword = Encrypt.isTextValid(password, verifyPassword);
				if(validPassword) {
						MongoDatabase db = MyUtils.getMongoConnection(request);
						try {
							user = MongoDBUtils.addUserAccount(firstName, lastName, userName, password);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							hasError = true;
							e.printStackTrace();
							errorString = e.getMessage();
							System.out.println("Error string: " + errorString);
						}
				}else {
					hasError = true;
					errorString = "Password does not contain uppercase, lowercase, and or numbers";
				}

			/*
			 * This is for JDBC Connection conn = MyUtils.getStoredConnection(request); try
			 * { user = DBUtils.addUser(conn, firstName, lastName, userName, password); }
			 * catch (Exception e) { // TODO Auto-generated catch block e.printStackTrace();
			 * hasError = true; errorString = e.getMessage();
			 * System.out.println("Error string: " + errorString);
			 * System.out.println("User created:" + user); }
			 */
		}

		if (hasError) {
			user = new UserAccounts();
			user.setUserName(userName);
			user.setFirstName(firstName);
			user.setLastName(lastName);

			request.setAttribute("errorString", errorString);
			request.setAttribute("user", user);
			RequestDispatcher dispatch = this.getServletContext().getRequestDispatcher("/WEB-INF/views/SignUp.jsp");
			dispatch.forward(request, response);
		} else {
			HttpSession session = request.getSession();

			MyUtils.storeLoginUser(session, user);
			response.sendRedirect(request.getContextPath() + "/UserInfoServlet");
		}

	}

}
