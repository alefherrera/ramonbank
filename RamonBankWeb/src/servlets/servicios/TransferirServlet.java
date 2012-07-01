package servlets.servicios;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ramon.ramonbank.dbaccess.tables.Clientes;
import com.ramon.ramonbank.dbaccess.tables.Cuentas;
import com.ramon.ramonbank.exceptions.OperationException;
import com.ramon.ramonbank.servicios.ServiciosCliente;

import scope.CuentasBean;
import scope.MessageBean;
import servlets.BaseServlet;

/**
 * Servlet implementation class TransferirServlet
 */
public class TransferirServlet extends BaseServiciosServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void Accion(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.Accion(request, response);
		String dir = new String();
		try {
			int Monto = Integer.parseInt(request.getParameter("Monto"));

			int idCuenta = Integer.parseInt(request.getParameter("idCuenta"));

			int idCuentaHasta = Integer.parseInt(request.getParameter("idCuentaHasta"));
			
			Cuentas cuenta = new Cuentas();
			cuenta.set_id(idCuenta);
			cuenta.Load();
			
			Cuentas cuentahasta = new Cuentas();
			cuentahasta.set_id(idCuentaHasta);
			
			servicio.transferir(cuenta, cuentahasta, Monto);
			dir = "/servicios/servicios.jsp";
			
		} catch (OperationException ex) {
			MessageBean bean = new MessageBean();
			bean.setMensaje(ex.getMessage());
			request.setAttribute("msjBean", bean);

			CuentasBean bean2 = new CuentasBean();
			bean2.setCuentas(servicio.listarCuentas(new Cuentas()));

			request.setAttribute("CuentasBean", bean2);

			dir = "/servicios/transferir.jsp";
		} finally {
			request.getRequestDispatcher(dir).forward(request, response);
		}
		
	}
       
    

}
