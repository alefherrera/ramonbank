package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scope.CuentasBean;
import scope.ServiciosBean;

import com.ramon.ramonbank.dbaccess.tables.Clientes;
import com.ramon.ramonbank.dbaccess.tables.Cuentas;
import com.ramon.ramonbank.dbaccess.tables.Servicios;
import com.ramon.ramonbank.servicios.ServiciosCliente;

/**
 * Servlet implementation class ComboLoadServicioServlet
 */
public class ComboLoadServicioServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void Accion(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		ServiciosBean bean = new ServiciosBean();
		Servicios servicio = new Servicios();
		bean.setServicios(servicio.LoadList());
		
		request.setAttribute("ServiciosBean", bean);
		
		ComboLoadServlet servletcombo = new ComboLoadServlet();
		servletcombo.Accion(request, response);

	}




}
