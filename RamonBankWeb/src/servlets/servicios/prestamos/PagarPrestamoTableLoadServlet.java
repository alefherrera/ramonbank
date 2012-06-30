package servlets.servicios.prestamos;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scope.PrestamosBean;
import servlets.servicios.BaseServiciosServlet;

import com.ramon.ramonbank.dbaccess.tables.Prestamos;

/**
 * Servlet implementation class PagarPrestamoLoadServlet
 */
public class PagarPrestamoTableLoadServlet extends BaseServiciosServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void Accion(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.Accion(request, response);
		PrestamosBean bean = new PrestamosBean();
		bean.setPrestamos(servicio.listarPrestamos(new Prestamos()));
		request.setAttribute("PrestamosBean", bean);
		
		request.getRequestDispatcher("/servicios/pagarprestamotable.jsp").forward(request, response);

	}

}

