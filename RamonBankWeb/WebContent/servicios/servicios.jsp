<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/header.jsp"></jsp:include>
<form id="formservicios" method="post">

	<input type="hidden" name="dir" id="dir" />
	<ul>
		<li>Prestamo
			<ul>
				<li><a href="#" id="solicitarprestamo" class="lnk">Solicitar
						Prestamo</a></li>
				<li><a href="#" id="pagarprestamo">Pagar Prestamo</a></li>
			</ul>
		<li><a href="#" id="pagarservicio" class="lnk">Pagar Servicio</a></li>
		<li><a href="#" id="depositar" class="lnk">Depositar </a></li>
		<li><a href="#" id="extraer" class="lnk">Extraer</a></li>
		<li><a href="#" id="transferir" class="lnk">Transferir</a></li>
		<li><a href="#" id="plazofijo" class="lnk">Plazo Fijo</a></li>
	</ul>

</form>
<script>
	$(".lnk").click(
			function() {
				$("#dir").val('/servicios/' + $(this).attr('id'));
				SubmitForm(document.forms["formservicios"],
						"/ramonbank/ComboLoadServlet");
			});
	$("#pagarprestamo").click(function() {
		CrearForm("/ramonbank/PagarPrestamoTableLoadServlet");
	});
</script>
<jsp:include page="/footer.jsp"></jsp:include>