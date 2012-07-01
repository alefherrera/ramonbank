<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/header.jsp"></jsp:include>
<jsp:useBean id="serviciosBean" scope="request"
	class="scope.ServiciosBean"></jsp:useBean>
<table border="1">
	<tr>
		<th>Fecha Alta</th>
		<th>Cuenta</th>
		<th>Servicio</th>
	</tr>
	<c:forEach items="${serviciosBean.pagoServicios}" var="servicios">
		<tr>
			<td><c:out value="${servicios.get_fechaAlta()}"></c:out></td>
			<td><c:out value="${servicios.get_nroCuenta()}"></c:out></td>
			<td><c:out value="${servicios.get_Servicio()}"></c:out></td>

		</tr>
	</c:forEach>
</table>
<jsp:include page="/footer.jsp"></jsp:include>