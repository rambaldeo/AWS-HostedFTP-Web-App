package planning.webapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import com.mongodb.client.MongoDatabase;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import planning.webapp.AboutPageDescriptions;
import planning.webapp.UserAccounts;
import planning.webapp.conn.MongoDBUtils;
import planning.webapp.utils.MyUtils;

/**
 * Servlet implementation class AboutWebApp
 */
@WebServlet(urlPatterns = {"/AboutWebApp"})
public class AboutWebAppServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AboutWebAppServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/About.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String Option = "Technical";
		MongoDatabase conn = MyUtils.getMongoConnection(request);
		try {
			MongoDBUtils.getDescrtiption(Option);
			AboutPageDescriptions techDes;
			System.out.println();
			request.getSession().setAttribute(Option, conn);
		}catch (Exception e) {
			
		}

	}

}





