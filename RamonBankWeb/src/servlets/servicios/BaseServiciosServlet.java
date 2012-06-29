package servlets.servicios;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ramon.ramonbank.businesslogic.ServiciosCliente;
import com.ramon.ramonbank.dbaccess.tables.Clientes;
import com.ramon.ramonbank.dbaccess.tables.Cuentas;

import servlets.BaseServlet;

/**
 * Servlet implementation class BaseServiciosServlet
 */
public class BaseServiciosServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	protected ServiciosCliente servicio;
	
	@Override
	protected void Accion(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Clientes cliente = (Clientes) request.getSession().getAttribute(
				"cliente");
		servicio = new ServiciosCliente(cliente);
		
	}
       
   

}
