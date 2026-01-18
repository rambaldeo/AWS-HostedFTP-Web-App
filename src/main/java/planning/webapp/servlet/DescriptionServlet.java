package planning.webapp.servlet;

import java.io.IOException;

import com.mongodb.client.MongoDatabase;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import planning.webapp.UserAccounts;
import planning.webapp.conn.MongoDBUtils;
import planning.webapp.utils.MyUtils;

/**
 * Servlet implementation class DescriptionServlet
 */
@WebServlet(urlPatterns = { "/description" })
public class DescriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DescriptionServlet() {
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
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/UserInfo.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String description = request.getParameter("description");
		String userName = request.getParameter("userName");

		if (description == null || description.trim().isEmpty()) {
			request.setAttribute("errorMessage", "Description cannot be empty.");
			doGet(request, response);
			return;
		}

		// Connection conn = MyUtils.getStoredConnection(request);
		MongoDatabase db = MyUtils.getMongoConnection(request);
		try {
			MongoDBUtils.updateDescription(userName, description);
			UserAccounts updatedUserDescription = MongoDBUtils.findUser(userName);
			if (updatedUserDescription != null) {
				request.getSession().setAttribute("user", updatedUserDescription);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Database error: " + e.getMessage());
		}
		// This is for SQL connection
//	    try {
//	        DBUtils.updateDescription(conn, userName, description);
//	        UserAccounts updatedUser = DBUtils.findUser(conn, userName);
//	        if (updatedUser != null) {
//	            request.getSession().setAttribute("user", updatedUser);
//	        }
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	        request.setAttribute("errorMessage", "Database error: " + e.getMessage());
//	    }
		doGet(request, response);
	}

}
