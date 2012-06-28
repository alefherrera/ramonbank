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
		<li><a href="#" id="SolicitarPrestamoServlet" class="lnk">Solicitar
				Prestamo</a></li>
		<li><a href="#" id="PagarPrestamoServlet" class="lnk">Pagar
				Prestamo</a></li>
		<li><a href="#" id="PagarServicioServlet" class="lnk">Pagar
				Servicio</a></li>
		<li><a href="#" id="DepositarServlet" class="lnk">Depositar </a></li>
		<li><a href="#" id="ExtraerServlet" class="lnk">Extraer</a></li>
		<li><a href="#" id="TransferirServlet" class="lnk">Transferir</a></li>
	</ul>


</form>
<script>
	$(".lnk").click(
			function() {
				SubmitForm(document.forms["formservicios"], "/ramonbank/"
						+ $(this).attr('id'));
			});
</script>
<jsp:include page="/footer.jsp"></jsp:include>