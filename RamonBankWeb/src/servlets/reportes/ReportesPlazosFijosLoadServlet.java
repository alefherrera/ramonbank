package servlets.reportes;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ramon.ramonbank.dbaccess.tables.Clientes;
import com.ramon.ramonbank.dbaccess.tables.PlazosFijos;
import com.ramon.ramonbank.servicios.Reportes;

import scope.PlazosFijosBean;
import servlets.BaseServlet;

/**
 * Servlet implementation class PlazosFijosLoad
 */
public class ReportesPlazosFijosLoadServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void Accion(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ArrayList<PlazosFijos> arrayplazosfijos = new ArrayList<PlazosFijos>();
		Clientes cliente = (Clientes) request
				.getSession().getAttribute("cliente");
		arrayplazosfijos = Reportes.reportePlazosFijos(cliente);
		
		PlazosFijosBean plazosBean = new PlazosFijosBean();
		plazosBean.setPlazosfijos(arrayplazosfijos);
		
		request.setAttribute("plazosfijosBean", plazosBean);
		request.getRequestDispatcher("/reportes/plazosfijos.jsp").forward(request, response);

	}
}
