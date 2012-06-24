package servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scope.CuentasBean;
import scope.MessageBean;

import com.ramon.ramonbank.businesslogic.ServiciosCliente;
import com.ramon.ramonbank.dbaccess.tables.Clientes;
import com.ramon.ramonbank.exceptions.OperationException;

/**
 * Servlet implementation class CrearCuentas
 */
public class CrearCuentaServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void Accion(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Clientes cliente = (Clientes) request.getSession().getAttribute(
				"cliente");
		ServiciosCliente servicio = new ServiciosCliente(cliente);
		int tipoCuenta = Integer.parseInt(request
				.getParameter("comboTipoCuenta"));
		String dir = new String();
		try {
			servicio.crearCuenta(tipoCuenta);
			CuentasServlet servlet = new CuentasServlet();
			servlet.Accion(request, response);
			dir = "/cuentas/cuentas.jsp";
			
		} catch (OperationException ex) {
			MessageBean bean = new MessageBean();
			bean.setMensaje(ex.getMessage());
			request.setAttribute("msjBean", bean);
			CrearCuentaLoadServlet serv = new CrearCuentaLoadServlet();
			serv.Accion(request, response);
		}
	}
}
