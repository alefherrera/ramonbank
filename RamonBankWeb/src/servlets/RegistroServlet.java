package servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scope.MessageBean;

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
		String dir = new String();
		Clientes cliente = new Clientes();
		cliente.set_dni(request.getParameter("dni"));
		cliente.set_nombre(request.getParameter("nombre"));
		cliente.set_apellido(request.getParameter("apellido"));
		cliente.set_direccion(request.getParameter("direccion"));
		cliente.set_email(request.getParameter("email"));
		try
		{
			ServiciosGeneral.crearCliente(cliente);
			request.getSession().setAttribute("cliente", cliente);
			dir = "index.jsp";			
		}
		catch(OperationException ex)
		{
			MessageBean bean = new MessageBean();
			bean.setMensaje(ex.getMessage());
			request.setAttribute("msjBean", bean);
			dir = "/registro/registro.jsp";
		}
		finally
		{
			request.getRequestDispatcher(dir).forward(request, response);
		}
		
		
	}
       
  
}
