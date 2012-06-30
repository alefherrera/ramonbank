<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="CuentasBean" scope="request" type="scope.CuentasBean"></jsp:useBean>
Cuentas:
<select name="idCuenta" id="idCuenta">

	<c:forEach items="${ CuentasBean.getCuentas() }" var="cuenta">
		<option value="${ cuenta.get_id() }">
			<c:out value="${ cuenta.get_id() } - ${ cuenta.get_tipo_nombre()}"></c:out>
		</option>
	</c:forEach>

</select>