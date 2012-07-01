<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/header.jsp"></jsp:include>
<jsp:useBean id="plazosfijosBean" scope="request"
	type="scope.PlazosFijosBean"></jsp:useBean>
<table border="1">
	<tr>
		<th>Fecha Alta</th>
		<th>Origen</th>
		<th>Cuenta de Origen</th>
		<th>Destino</th>
		<th>Cuenta de Destino</th>
		<th>Fecha Vencimiento</th>
		<th>Monto</th>
		<th>Interés</th>
		<th>Activo</th>

	</tr>
	<c:forEach items="${plazosfijosBean.plazosfijos}" var="plazofijo">
		<tr>
			<td><c:out value="${plazofijo.get_fechaAlta()}"></c:out></td>
			<td><c:out value="${plazofijo.get_origen_nombre()}"></c:out></td>
			<td><c:out value="${plazofijo.get_nroCuentaOrigen()}"></c:out></td>
			<td><c:out value="${plazofijo.get_acreditacion_nombre()}"></c:out></td>
			<td><c:out value="${plazofijo.get_nroCuentaDestino()}"></c:out></td>
			<td><c:out value="${plazofijo.get_fechaVencimiento()}"></c:out></td>
			<td><c:out value="${plazofijo.get_monto()}"></c:out></td>
			<td><c:out value="${plazofijo.get_interes()}"></c:out></td>
			<td><c:out value="${plazofijo.get_activo()}"></c:out></td>
		</tr>
	</c:forEach>
</table>
<jsp:include page="/footer.jsp"></jsp:include>