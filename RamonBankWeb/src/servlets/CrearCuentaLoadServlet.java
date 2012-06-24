package servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scope.CuentasBean;


/**
 * Servlet implementation class CrearCuentaServlet
 */
public class CrearCuentaLoadServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void Accion(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setAttribute("CuentasBean",new CuentasBean());
		request.getRequestDispatcher("/cuentas/crearcuenta.jsp").forward(request, response);
	}

}
