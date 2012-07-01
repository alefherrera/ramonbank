package servlets.servicios;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ramon.ramonbank.dbaccess.tables.Clientes;
import com.ramon.ramonbank.dbaccess.tables.Cuentas;
import com.ramon.ramonbank.exceptions.OperationException;
import com.ramon.ramonbank.servicios.ServiciosCliente;
import com.ramon.ramonbank.utils.Fecha;

import scope.CuentasBean;
import scope.MessageBean;
import servlets.BaseServlet;

/**
 * Servlet implementation class PlazoFijoServlet
 */
public class PlazoFijoServlet extends BaseServiciosServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void Accion(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.Accion(request, response);
		String dir = new String();
		try {
			int Monto = Integer.parseInt(request.getParameter("Monto"));
			int idCuentaDestino = Integer.parseInt(request
					.getParameter("idCuentaDestino"));
			int idCuentaOrigen = Integer.parseInt(request
					.getParameter("idCuentaOrigen"));
			Fecha FechaVencimiento = new Fecha();
			FechaVencimiento
					.set_Fecha(request.getParameter("FechaVencimiento"));
			if (idCuentaOrigen == 0) {
				if (idCuentaDestino == 0) {
					servicio.plazoFijo(Monto, FechaVencimiento);
				} else {
					Cuentas cuentaDestino = new Cuentas();
					cuentaDestino.set_id(idCuentaDestino);
					servicio.plazoFijo(Monto, FechaVencimiento, cuentaDestino);
				}

			} else {
				if (idCuentaDestino == 0) {
					Cuentas cuentaOrigen = new Cuentas();
					cuentaOrigen.set_id(idCuentaOrigen);
					servicio.plazoFijo(cuentaOrigen, Monto, FechaVencimiento);
				} else {
					Cuentas cuentaOrigen = new Cuentas();
					cuentaOrigen.set_id(idCuentaOrigen);
					Cuentas cuentaDestino = new Cuentas();
					cuentaDestino.set_id(idCuentaDestino);
					servicio.plazoFijo(cuentaOrigen, Monto, FechaVencimiento,
							cuentaDestino);
				}

			}
			dir = "/servicios/servicios.jsp";

		} catch (OperationException ex) {
			MessageBean bean = new MessageBean();
			bean.setMensaje(ex.getMessage());
			request.setAttribute("msjBean", bean);

			CuentasBean bean2 = new CuentasBean();
			bean2.setCuentas(servicio.listarCuentas(new Cuentas()));

			request.setAttribute("CuentasBean", bean2);

			dir = "/servicios/plazofijo.jsp";
		} finally {
			request.getRequestDispatcher(dir).forward(request, response);
		}
	}
}
