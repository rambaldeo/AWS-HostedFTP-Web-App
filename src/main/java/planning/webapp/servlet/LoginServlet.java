package planning.webapp.servlet;

import java.io.IOException;

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
import planning.webapp.utils.MyUtils;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/Login.jsp");
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
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		UserAccounts user = null;
		boolean hasError = false;
		String errorString = null;

		if (userName == null || password == null || userName.length() == 0 || password.length() == 0) {
			hasError = true;
			errorString = "Require username and password";
		} else {
			MongoDatabase db = MyUtils.getMongoConnection(request);
			try {
				user = MongoDBUtils.findUser(userName, password);
				if (user == null) {
					hasError = true;
					errorString = "Invalid username or password";
					System.out.print("Cannot find user");
				}
			} catch (Exception e) {
				e.printStackTrace();
				hasError = true;
				errorString = e.getMessage();
			}
			/*
			 * Connection conn = MyUtils.getStoredConnection(request); try { user =
			 * DBUtils.findUser(conn, userName, password); if (user == null) { hasError =
			 * true; errorString = "Invalid Username or Password"; }
			 *
			 * }catch (Exception e) { e.printStackTrace(); hasError = true; errorString =
			 * e.getMessage(); }
			 */
		}

		if (hasError) {
			user = new UserAccounts();
			user.setUserName(userName);

			request.setAttribute("errorString", errorString);
			request.setAttribute("user", user);

			RequestDispatcher dispatch = this.getServletContext().getRequestDispatcher("/WEB-INF/views/Login.jsp");
			dispatch.forward(request, response);
		} else {
			HttpSession session = request.getSession();

			MyUtils.storeLoginUser(session, user);
			// request.getSession().setAttribute("loginedUser", user);
			// forward to userInfo page
			
			
			response.sendRedirect(request.getContextPath() + "/UserInfoServlet");
		}
	}

}
