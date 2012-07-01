package servlets.reportes;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ramon.ramonbank.dbaccess.tables.PagoServicios;
import com.ramon.ramonbank.dbaccess.tables.Clientes;
import com.ramon.ramonbank.servicios.Reportes;

import scope.ServiciosBean;
import servlets.BaseServlet;

/**
 * Servleet implementation class ServiciosPagadosLoadServlet
 */
public class ReportesServiciosPagadosLoadServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void Accion(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ArrayList<PagoServicios> arrayservicios;
		arrayservicios = Reportes.serviciosPagados((Clientes) request
				.getSession().getAttribute("cliente"));
		
		ServiciosBean servBean = new ServiciosBean();
		servBean.setPagoServicios(arrayservicios);
		
		request.setAttribute("serviciosBean", servBean);
		request.getRequestDispatcher("/reportes/serviciospagados.jsp").forward(request, response);
	}
}
