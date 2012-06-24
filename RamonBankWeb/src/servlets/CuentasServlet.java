package servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ramon.ramonbank.businesslogic.ServiciosCliente;
import com.ramon.ramonbank.dbaccess.tables.Clientes;
import com.ramon.ramonbank.dbaccess.tables.Cuentas;

import scope.CuentasBean;

/**
 * Servlet implementation class CuentasServlet
 */
public class CuentasServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void Accion(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ServiciosCliente service = new ServiciosCliente((Clientes)request.getSession().getAttribute("cliente"));
		
		CuentasBean bean = new CuentasBean();
		bean.setCuentas(service.listarCuentas(new Cuentas()));
		
		request.setAttribute("CuentasBean", bean);
		request.getRequestDispatcher("/cuentas/cuentas.jsp").forward(request, response);
		
	}

}
