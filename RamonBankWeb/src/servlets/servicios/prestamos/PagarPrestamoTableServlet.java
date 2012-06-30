package servlets.servicios.prestamos;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ramon.ramonbank.dbaccess.tables.Cuentas;
import com.ramon.ramonbank.dbaccess.tables.Prestamos;


import scope.CuentasBean;
import scope.PrestamosBean;
import servlets.servicios.BaseServiciosServlet;

/**
 * Servlet implementation class PagarPrestamoTableServlet
 */
public class PagarPrestamoTableServlet extends BaseServiciosServlet {       
	private static final long serialVersionUID = 1L;

	@Override
	protected void Accion(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.Accion(request, response);

		PrestamosBean bean = new PrestamosBean();
		Prestamos prestamo = new Prestamos();
		prestamo.set_id(Integer.parseInt(request.getParameter("idPrestamo")));
		bean.setPrestamo(prestamo);
		
		CuentasBean bean2 = new CuentasBean();
		bean2.setCuentas(servicio.listarCuentas(new Cuentas()));

		request.setAttribute("CuentasBean", bean2);
		
		request.setAttribute("PrestamosBean", bean);
		request.getRequestDispatcher("/servicios/pagarprestamo.jsp").forward(request, response);

	}

}
