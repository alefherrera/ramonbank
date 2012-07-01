package servlets.servicios;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ramon.ramonbank.dbaccess.tables.Clientes;
import com.ramon.ramonbank.dbaccess.tables.Cuentas;
import com.ramon.ramonbank.dbaccess.tables.Servicios;
import com.ramon.ramonbank.exceptions.OperationException;
import com.ramon.ramonbank.servicios.ServiciosCliente;

import scope.CuentasBean;
import scope.MessageBean;
import scope.ServiciosBean;
import servlets.BaseServlet;

/**
 * Servlet implementation class PagarServicioServlet
 */
public class PagarServicioServlet extends BaseServiciosServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void Accion(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		super.Accion(request, response);
		String dir = new String();
		try {
			int idServicio = Integer.parseInt(request
					.getParameter("idServicio"));
			
			Cuentas cuenta = new Cuentas();
			cuenta.set_id(Integer.parseInt(request.getParameter("idCuenta")));
			cuenta.Load();
			
			servicio.pagarServicio(idServicio, cuenta);
			dir = "/servicios/servicios.jsp";
			
		} catch (OperationException ex) {
			MessageBean bean = new MessageBean();
			bean.setMensaje(ex.getMessage());
			request.setAttribute("msjBean", bean);

			CuentasBean bean2 = new CuentasBean();
			bean2.setCuentas(servicio.listarCuentas(new Cuentas()));

			request.setAttribute("CuentasBean", bean2);
			
			ServiciosBean bean3 = new ServiciosBean();
			Servicios servicio = new Servicios();
			bean3.setServicios(servicio.LoadList());
			
			request.setAttribute("ServiciosBean", bean3);

			dir = "/servicios/pagarservicio.jsp";
		} finally {
			request.getRequestDispatcher(dir).forward(request, response);

		}
		
		
		
		
	}
       
    

}
