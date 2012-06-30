<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/header.jsp"></jsp:include>
<jsp:useBean id="PrestamosBean" scope="request"
	type="scope.PrestamosBean"></jsp:useBean>
<table border="1">
	<c:if test="${PrestamosBean != null}">
		<tr>
			
			<th>Fecha Alta</th>
			<th>Cuotas</th>
			<th>Monto</th>
			<th>Interes</th>
			<th>Pagar</th>
		</tr>
		<c:forEach items="${PrestamosBean.prestamos}" var="prestamo">

			<tr>
				<td><c:out value="${prestamo.get_fechaAlta()}"></c:out></td>
				<td><c:out value="${prestamo.get_cantCuotas()}"></c:out></td>
				<td><c:out value="${prestamo.get_monto()}"></c:out></td>
				<td><c:out value="${prestamo.get_interes()}"></c:out></td>
				<td><a href="#" id="${prestamo.get_id()}" class="lnk">Pagar</a>

			</tr>

		</c:forEach>
	</c:if>
</table>
<script>
	$(".lnk").click(
			function() {
				var form = document.createElement("form");
				form.method = 'post';
				var hidden = document.createElement("input");
				hidden.type = 'hidden';
				hidden.value = $(this).attr('id');
				hidden.id = "idPrestamo";
				hidden.name = "idPrestamo";
				form.appendChild(hidden);				
				SubmitForm(form,
						"/ramonbank/PagarPrestamoTableServlet");
			});
</script>
<jsp:include page="/footer.jsp"></jsp:include>