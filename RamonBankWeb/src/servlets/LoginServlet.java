package servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void Accion(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.getSession().setAttribute("logon_user", request.getParameter("username"));
		request.getSession().setAttribute("pass", request.getParameter("password"));
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	

}
