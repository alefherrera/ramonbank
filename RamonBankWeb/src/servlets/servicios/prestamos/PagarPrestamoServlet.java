package servlets.servicios.prestamos;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ramon.ramonbank.dbaccess.tables.Clientes;
import com.ramon.ramonbank.dbaccess.tables.Cuentas;
import com.ramon.ramonbank.dbaccess.tables.Prestamos;
import com.ramon.ramonbank.exceptions.OperationException;
import com.ramon.ramonbank.servicios.ServiciosCliente;

import scope.CuentasBean;
import scope.MessageBean;
import scope.PrestamosBean;
import servlets.BaseServlet;
import servlets.servicios.BaseServiciosServlet;

/**
 * Servlet implementation class PagarPrestamoServlet
 */
public class PagarPrestamoServlet extends BaseServiciosServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void Accion(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.Accion(request, response);
		String dir = new String();
		try {
			Prestamos prestamo = new Prestamos();
			prestamo.set_id(Integer.parseInt(request.getParameter("idPrestamo")));
			prestamo.Load();

			String cantidadCuotas = request.getParameter("cantidadCuotas");

			int idCuenta = Integer.parseInt(request.getParameter("idCuenta"));

			if (idCuenta == 0) {
				servicio.pagarPrestamo(prestamo, cantidadCuotas);
			} else {
				Cuentas cuenta = new Cuentas();
				cuenta.set_id(idCuenta);
				cuenta.Load();

				servicio.pagarPrestamo(prestamo, cantidadCuotas, cuenta);
			}
			PrestamosBean bean = new PrestamosBean();
			bean.setPrestamos(servicio.listarPrestamos(new Prestamos()));
			request.setAttribute("PrestamosBean", bean);
			dir = "/servicios/pagarprestamotable.jsp";
		} catch (OperationException ex) {
			MessageBean bean = new MessageBean();
			bean.setMensaje(ex.getMessage());
			request.setAttribute("msjBean", bean);
			PrestamosBean bean2 = new PrestamosBean();
			Prestamos prestamo = new Prestamos();
			prestamo.set_id(Integer.parseInt(request.getParameter("idPrestamo")));
			bean2.setPrestamo(prestamo);
			request.setAttribute("PrestamosBean", bean2);

			CuentasBean bean3 = new CuentasBean();
			bean3.setCuentas(servicio.listarCuentas(new Cuentas()));

			request.setAttribute("CuentasBean", bean3);

			dir = "/servicios/pagarprestamo.jsp";
		} finally {
			request.getRequestDispatcher(dir).forward(request, response);

		}
	}

}
