<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/header.jsp"></jsp:include>
<jsp:useBean id="CuentasBean" scope="request" type="scope.CuentasBean"></jsp:useBean>
<form id="formservicios" method="post">

	<select name="comboCuenta">

		<c:forEach items="${ CuentasBean.getCuentas() }" var="cuenta">
			<option value="${ cuenta.get_id() }">
				<c:out value="${ cuenta.get_id() } - ${ cuenta.get_tipo_nombre()}"></c:out>
			</option>
		</c:forEach>

	</select>
	<ul>
		<li><a href="#" id="lnksolicitarprestamo">Solicitar Prestamo</a></li>
		<li><a href="#" id="lnkpagarservicio">Pagar Servicio</a></li>
	</ul>


</form>
<script>
	$("#lnksolicitarprestamo").click(function() {
		document.forms["formservicios"].action = "/ramonbank/ServiciosServlet";
		document.forms["formservicios"].submit();
	});
	$("#lnkpagarservicio")
			.click(
					function() {
						document.forms["formservicios"].action = "/ramonbank/PagarServicioServlet";
						document.forms["formservicios"].submit();
					});
</script>
<jsp:include page="/footer.jsp"></jsp:include>