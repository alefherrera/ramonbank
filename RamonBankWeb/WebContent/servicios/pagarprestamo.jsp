<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/header.jsp"></jsp:include>
<jsp:useBean id="PrestamosBean" scope="request"
	type="scope.PrestamosBean"></jsp:useBean>
<form method="post" action="/ramonbank/PagarPrestamoServlet">
	<c:if test="${PrestamosBean != null}">		
		<input type="hidden" value="${PrestamosBean.prestamo.get_id()}" name="idPrestamo" id="idPrestamo" />
	</c:if>
	Cuotas:<input type="text"
		name="cantidadCuotas" id="cantidadCuotas" />
	<jsp:include page="/combocuenta.jsp"></jsp:include>
	<input type="submit" value="Pagar">
</form>
<script type="text/javascript">
	$(document).ready(function () { $("#idCuenta").append("<option value='0'>Efectivo</option>"); });
</script>
<jsp:include page="/footer.jsp"></jsp:include>