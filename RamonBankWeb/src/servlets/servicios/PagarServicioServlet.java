package servlets.servicios;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ramon.ramonbank.dbaccess.tables.Clientes;
import com.ramon.ramonbank.dbaccess.tables.Cuentas;
import com.ramon.ramonbank.servicios.ServiciosCliente;

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
		
		int idServicio = Integer.parseInt(request
				.getParameter("idServicio"));
		
		Cuentas cuenta = new Cuentas();
		cuenta.set_id(Integer.parseInt(request.getParameter("idCuenta")));
		cuenta.Load();
		
		servicio.pagarServicio(idServicio, cuenta);
		
	}
       
    

}
