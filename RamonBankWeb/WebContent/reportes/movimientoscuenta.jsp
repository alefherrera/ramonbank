<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/header.jsp"></jsp:include>
<jsp:useBean id="movimientosBean" scope="request"
	class="scope.MovimientosBean"></jsp:useBean>
<form method="post"
	action="/ramonbank/ReportesDepositoExtraccionLoadServlet">
	<jsp:include page="/combocuenta.jsp"></jsp:include>
	<input type="text" id="FechaDesde" name="FechaDesde" />
	<input type="text" id="FechaHasta" name="FechaHasta" />
	<select id="tipoMovimiento" name="tipoMovimiento">
	<c:forEach items="${ movimientosBean.getTipos() }" var="tipo">
			<option value="${ tipo.id() }">
				<c:out value="${ tipo.nombre() }"></c:out>
			</option>
		</c:forEach>
	</select>
	 <input type="submit" value="Cargar" />
</form>
<c:if test="${movimientosBean.movimientos != null}">
	<table border="1">
		<tr>
			<th>Fecha Alta</th>
			<th>Cuenta</th>
			<th>Tipo</th>
			<th>Origen</th>
			<th>Saldo</th>
			<th>Monto</th>
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