<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/header.jsp"></jsp:include>
<jsp:useBean id="movimientosBean" scope="request"
	class="scope.MovimientosBean"></jsp:useBean>
<form method="post" action="/ramonbank/ReportesTransferenciasLoadServlet">
	<jsp:include page="/combocuenta.jsp"></jsp:include>
	<br /> <input type="submit" value="Cargar" />
</form>
<c:if test="${movimientosBean.movimientos != null}">
	<table border="1">
		<tr>
			<th>Fecha Alta</th>
			<th>Cuenta</th>
			<th>Servicio</th>
		</tr>
		<c:forEach items="${movimientosBean.movimientos}" var="movimiento">
			<tr>
				<td><c:out value="${movimiento.get_fecha()}"></c:out></td>
				<td><c:out value="${movimiento.get_idcuenta()}"></c:out></td>
				<td><c:out value="${movimiento.get_tipo_nombre()}"></c:out></td>
				<td><c:out value="${movimiento.get_origen_nombre()}"></c:out></td>
				<td><c:out value="${movimiento.get_saldo()}"></c:out></td>
				<td><c:out value="${movimiento.get_monto()}"></c:out></td>
			</tr>
		</c:forEach>
	</table>
</c:if>
<jsp:include page="/footer.jsp"></jsp:include>