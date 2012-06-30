package servlets.servicios.prestamos;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ramon.ramonbank.dbaccess.tables.Clientes;
import com.ramon.ramonbank.dbaccess.tables.Cuentas;
import com.ramon.ramonbank.dbaccess.tables.Prestamos;
import com.ramon.ramonbank.servicios.ServiciosCliente;

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

		Prestamos prestamo = new Prestamos();
		prestamo.set_id(Integer.parseInt(request.getParameter("idPrestamo")));
		prestamo.Load();

		int cantidadCuotas = Integer.parseInt(request
				.getParameter("cantidadCuotas"));

		Cuentas cuenta = new Cuentas();
		cuenta.set_id(Integer.parseInt(request.getParameter("idCuenta")));
		cuenta.Load();

		servicio.pagarPrestamo(prestamo, cantidadCuotas, cuenta);
		
		PrestamosBean bean = new PrestamosBean();
		bean.setPrestamos(servicio.listarPrestamos(new Prestamos()));
		request.setAttribute("PrestamosBean", bean);
		
		request.getRequestDispatcher("/servicios/pagarprestamotable.jsp").forward(request, response);

	}

}
