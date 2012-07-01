package servlets.reportes;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ramon.ramonbank.dbaccess.tables.Cuentas;
import com.ramon.ramonbank.dbaccess.tables.Movimientos;
import com.ramon.ramonbank.servicios.Reportes;

import scope.CuentasBean;
import scope.MovimientosBean;
import servlets.servicios.BaseServiciosServlet;

/**
 * Servlet implementation class ReportesDepositoTransferenciaLoadServlet
 */
public class ReportesDepositoExtraccionLoadServlet extends BaseServiciosServlet  {
	private static final long serialVersionUID = 1L;
       

	@Override
	protected void Accion(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.Accion(request, response);
		
		Cuentas cuenta = new Cuentas();
		cuenta.set_id(Integer.parseInt(request.getParameter("idCuenta")));
		ArrayList<Movimientos> movimientos = Reportes.transferenciasCuenta(cuenta);

		MovimientosBean movBean = new MovimientosBean();
		movBean.setMovimientos(movimientos);

		request.setAttribute("movimientosBean", movBean);
		
		CuentasBean bean = new CuentasBean();
		bean.setCuentas(servicio.listarCuentas(new Cuentas()));

		request.setAttribute("CuentasBean", bean);
		request.getRequestDispatcher("/reportes/movimientoscuenta.jsp").forward(request, response);
		
	}
}
