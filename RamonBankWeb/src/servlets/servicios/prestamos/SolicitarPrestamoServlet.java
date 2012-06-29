package servlets.servicios.prestamos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ramon.ramonbank.businesslogic.ServiciosCliente;
import com.ramon.ramonbank.dbaccess.tables.Clientes;
import com.ramon.ramonbank.dbaccess.tables.Cuentas;
import com.ramon.ramonbank.exceptions.OperationException;

import scope.CuentasBean;
import scope.MessageBean;
import servlets.BaseServlet;
import servlets.CrearCuentaLoadServlet;
import servlets.servicios.BaseServiciosServlet;

/**
 * Servlet implementation class SolicitarPrestamoServlet
 */
public class SolicitarPrestamoServlet extends BaseServiciosServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void Accion(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String dir = new String();
		super.Accion(request, response);
		int cantPrestamo = Integer.parseInt(request
				.getParameter("cantPrestamo"));
		int cantCuotas = Integer.parseInt(request.getParameter("cantCuotas"));
		int idCuenta = Integer.parseInt(request.getParameter("idCuenta"));
		boolean Descubierto = Boolean.parseBoolean(request
				.getParameter("Descubierto"));
		try {

			if (idCuenta == 0) {
				servicio.solicitarPrestamo(cantPrestamo, cantCuotas);
			} else {
				if (Descubierto) {
					servicio.solicitarPrestamoDescubierto(cantPrestamo,
							cantCuotas, idCuenta);
				} else {
					servicio.solicitarPrestamo(cantPrestamo, cantCuotas,
							idCuenta);
				}
			}
			dir = "/servicios/pagarprestamo.jsp";
		} catch (OperationException ex) {
			MessageBean bean = new MessageBean();
			bean.setMensaje(ex.getMessage());
			request.setAttribute("msjBean", bean);
			CuentasBean cuentabean = new CuentasBean();
			cuentabean.setCuentas(servicio.listarCuentas(new Cuentas()));
			request.setAttribute("CuentasBean", cuentabean);
			dir = "/servicios/solicitarprestamo.jsp";
		}
		finally
		{
			request.getRequestDispatcher(dir).forward(request, response);
		}

	}
}
