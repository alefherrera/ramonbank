package servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ramon.ramonbank.businesslogic.ServiciosGeneral;
import com.ramon.ramonbank.dbaccess.tables.Clientes;
import com.ramon.ramonbank.exceptions.OperationException;

/**
 * Servlet implementation class RegistroServlet
 */
public class RegistroServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void Accion(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Clientes cliente = new Clientes();
		cliente.set_dni(request.getParameter("dni"));
		cliente.set_nombre(request.getParameter("nombre"));
		cliente.set_apellido(request.getParameter("apellido"));
		cliente.set_direccion(request.getParameter("direccion"));
		cliente.set_email(request.getParameter("email"));
		try
		{
			ServiciosGeneral.crearCliente(cliente);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		catch(OperationException ex)
		{
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
		}
		
			

		
		
	}
       
  
}
