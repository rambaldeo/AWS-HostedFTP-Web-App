package planning.webapp.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import planning.webapp.UserAccounts;
import planning.webapp.openai.generateResponse;
import planning.webapp.utils.MyUtils;

/**
 * Servlet implementation class UserInfoServlet
 */
@WebServlet(urlPatterns = { "/UserInfoServlet" })
public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserInfoServlet() {
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
		HttpSession session = request.getSession();

		UserAccounts loginedUser = MyUtils.getLoginedUser(session);
		request.setAttribute("user", loginedUser);
		
		String quote = generateResponse.openAi();
		request.setAttribute("aiQuote", quote);
		List<String> quotes = (List<String>) session.getAttribute("quoteHistory");
		
		if (quotes == null) {
			quotes = new ArrayList<>();
		}
		quotes.add(quote);
		session.setAttribute("quoteHistory", quotes);
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
		// TODO Auto-generated method stub

		doGet(request, response);
	}

}
