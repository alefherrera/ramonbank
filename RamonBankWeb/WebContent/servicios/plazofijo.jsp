<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/header.jsp"></jsp:include>
<jsp:useBean id="CuentasBean" scope="request"
	type="scope.CuentasBean"></jsp:useBean>
<form method="post" action="/ramonbank/PlazoFijoServlet">

Cuenta Origen:
<select name="idCuentaOrigen" id="idCuentaOrigen">

	<c:forEach items="${ CuentasBean.getCuentas() }" var="cuenta">
		<option value="${ cuenta.get_id() }">
			<c:out value="${ cuenta.get_id() } - ${ cuenta.get_tipo_nombre()}"></c:out>
		</option>
	</c:forEach>

</select>

Monto: <input type="text" id="Monto" name="Monto" />
Fecha: <input type="text" id="Fecha" name="FechaVencimiento" />

Cuenta Destino:
<select name="idCuentaDestino" id="idCuentaDestino">

	<c:forEach items="${ CuentasBean.getCuentas() }" var="cuenta">
		<option value="${ cuenta.get_id() }">
			<c:out value="${ cuenta.get_id() } - ${ cuenta.get_tipo_nombre()}"></c:out>
		</option>
	</c:forEach>

</select>
<input type="submit" value="Crear">
</form>
<script type="text/javascript">
	$(document).ready(function () { $("#idCuentaDestino,#idCuentaOrigen").append("<option value='0'>Efectivo</option>"); });
</script>
<jsp:include page="/footer.jsp"></jsp:include>