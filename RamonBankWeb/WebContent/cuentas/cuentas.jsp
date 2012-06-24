<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/header.jsp"></jsp:include>
<jsp:useBean id="CuentasBean" scope="request" type="scope.CuentasBean"></jsp:useBean>

<table border="1">
	<c:if test="${CuentasBean != null}">
		<tr>
			<th>Tipo</th>
			<th>Saldo</th>
			<th>Descubierto</th>
		</tr>
		<c:forEach items="${CuentasBean.cuentas}" var="cuenta">

			<tr>
				<td><c:out value="${cuenta.get_tipo_nombre()}"></c:out></td>

				<td><c:out value="${cuenta.get_saldo()}"></c:out></td>
				<td><c:out value="${cuenta.get_descubierto()}"></c:out></td>
			</tr>

		</c:forEach>
	</c:if>
</table>
<form method="post" action="/ramonbank/CrearCuentaLoadServlet">
	<input type="submit" value="Crear Cuenta">
</form>
<jsp:include page="/footer.jsp"></jsp:include>