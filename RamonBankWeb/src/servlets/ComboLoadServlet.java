package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ramon.ramonbank.dbaccess.tables.Clientes;
import com.ramon.ramonbank.dbaccess.tables.Cuentas;
import com.ramon.ramonbank.servicios.ServiciosCliente;

import scope.CuentasBean;

/**
 * Servlet implementation class ComboLoadServlet
 */
public class ComboLoadServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void Accion(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ServiciosCliente service = new ServiciosCliente((Clientes) request
				.getSession().getAttribute("cliente"));

		CuentasBean bean = new CuentasBean();
		bean.setCuentas(service.listarCuentas(new Cuentas()));

		request.setAttribute("CuentasBean", bean);
		request.getRequestDispatcher("/servicios/"+ request.getParameter("dir") + ".jsp").forward(request, response);
		
	}
       
    

}
