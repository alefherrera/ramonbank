package servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogOurServlet
 */
public class LogOutServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void Accion(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.getSession().invalidate();
		request.getRequestDispatcher("index.jsp").forward(request, response);

	}

}
