package servlets;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scope.MessageBean;

import com.ramon.ramonbank.businesslogic.ServiciosGeneral;
import com.ramon.ramonbank.dbaccess.tables.Clientes;
import com.ramon.ramonbank.exceptions.OperationException;

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

	}

	@Override
	protected void Accion(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String dir = new String();
		try {			
				Clientes cliente = new Clientes();
				cliente.set_dni(request.getParameter("dni"));
				cliente = ServiciosGeneral.loguear(cliente);
				request.getSession().setAttribute("cliente", cliente);				
				dir = "index.jsp";

		} catch (OperationException ex) {
			MessageBean bean = new MessageBean();
			bean.setMensaje(ex.getMessage());
			request.setAttribute("msjBean", bean);
			dir = "/login/login.jsp";
		} finally {
			request.getRequestDispatcher(dir).forward(request, response);
		}

	}

}
