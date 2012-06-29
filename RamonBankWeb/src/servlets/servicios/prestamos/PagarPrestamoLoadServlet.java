package servlets.servicios.prestamos;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlets.servicios.BaseServiciosServlet;

import com.ramon.ramonbank.dbaccess.tables.Cuentas;
import com.ramon.ramonbank.dbaccess.tables.Prestamos;

/**
 * Servlet implementation class PagarPrestamoLoadServlet
 */
public class PagarPrestamoLoadServlet extends BaseServiciosServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void Accion(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.Accion(request, response);
		
		request.getRequestDispatcher("/servicios/pagarprestamo.jsp").forward(request, response);

	}

}

