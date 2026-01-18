package planning.webapp.filter;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import com.mongodb.client.MongoDatabase;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import planning.webapp.conn.MongoDBConn;
import planning.webapp.utils.MyUtils;

@WebFilter(filterName = "jdbcFilter", urlPatterns = { "/*" })
public class JDBC implements Filter {

	public JDBC() {

	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {

	}

	@Override
	public void destroy() {

	}

	private boolean needJDBC(HttpServletRequest request) {
		System.out.println("JDBC filter has been called");
		String serveltPath = request.getServletPath();
		String pathInfo = request.getPathInfo();

		String urlPattern = serveltPath;
		if (pathInfo != null) {
			urlPattern = serveltPath + "/";
		}

		Map<String, ? extends ServletRegistration> servletRegistration = request.getServletContext()
				.getServletRegistrations();

		// Collection of all servlet in my webapp
		Collection<? extends ServletRegistration> values = servletRegistration.values();
		for (ServletRegistration sr : values) {
			Collection<String> mappings = sr.getMappings();
			if (mappings.contains(urlPattern)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;

		if (this.needJDBC(req)) {
			System.out.println("Opening MongoDB Connection for: " + req.getServletPath());
			MongoDatabase db = null;
			try {
				db = MongoDBConn.getDatabase();
				MyUtils.storeMongoConnection(request, db);
				chain.doFilter(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException(e);
			}
		} else {
			chain.doFilter(request, response);
		}

//		This is for SQL connection
//		if(this.needJDBC(req)) {
//			System.out.println("Open connection for: " + req.getServletPath());
//			Connection conn = null;
//			try {
//
//				conn = ConnectionUtils.getConnection();
//				//conn = MongoDBConn.getDatabase();
//				conn.setAutoCommit(false);
//				MyUtils.storeConnection(request, conn);
//				chain.doFilter(request, response);
//				conn.commit();
//			}catch (Exception e) {
//				e.printStackTrace();
//				throw new ServletException();
//			}finally {
//				//change
//				ConnectionUtils.closeQuietly(conn);
//			}
//		}else {
//			chain.doFilter(request, response);
//		}

	}

}
