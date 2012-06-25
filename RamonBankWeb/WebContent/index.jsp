<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/header.jsp"></jsp:include>
<table>
	<tr>

		<c:if test="${sessionScope.cliente != null }">
			<ul>
				<li><a href="#" id="lnkCuentas">Cuentas</a></li>
				<li><a href="#" id="lnkServicios">Servicios</a></li>
			</ul>
		</c:if>

	</tr>
</table>
<form method="post" id="formredir"></form>
<script>
	$("#lnkCuentas").click(function() {
		CrearForm("/ramonbank/CuentasServlet");
	});
	$("#lnkServicios").click(function() {
		CrearForm("/ramonbank/ServiciosLoadServlet");
	});
</script>
<jsp:include page="/footer.jsp"></jsp:include>
