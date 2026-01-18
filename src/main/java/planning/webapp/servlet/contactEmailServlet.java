package planning.webapp.servlet;

import jakarta.mail.MessagingException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import planning.webapp.utils.EmailUtils;

/**
 * Servlet implementation class contactEmailServlet
 */
@WebServlet(urlPatterns = {"/sendSupportEmail"})
public class contactEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public contactEmailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    //Safe to sanitize user input
    private static String safe(String input) {
    	if (input == null) {
    		return "";
    	}
    	return input.replaceAll("{\\r\\n}", "").trim();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/contactPage.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String firstName = safe(request.getParameter("firstName"));
		String lastName = safe(request.getParameter("lastName"));
		String sender = safe(request.getParameter("email"));
		String country = safe(request.getParameter("country"));
		String companyName = safe(request.getParameter("companyName"));
		String subject = safe(request.getParameter("subject"));
		String body = safe(request.getParameter("body"));
		
		String emailBody ="New Support Request from HostedFTP\n" 
				+ "Name: " + firstName + " " + lastName + "\n"
				+ "Email: " + sender + "\n"
				+ "Company: " + companyName + "\n"
				+ "Country: " + country + "\n"
				+ "Message: " + body;
		
		try {
			EmailUtils.sendEmail(sender, subject, emailBody);
			RequestDispatcher dispatch = this.getServletContext().getRequestDispatcher("/WEB-INF/views/UserInfo.jsp");
			dispatch.forward(request, response);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**
		 * Need to add the code to take the request and send it to my personal email 
		 * */
	}

}
