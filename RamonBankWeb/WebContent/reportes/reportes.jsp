<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="/header.jsp"></jsp:include>
<form id="formservicios" method="post">

	<input type="hidden" name="dir" id="dir" />

	<ul>
		<li><a href="#" id="ultimosmovimientos" class="lnk">Ultimos
				Movimientos</a></li>
		<li><a href="#" id="movimientoscuenta" class="lnk">Depůsitos
				o Extracciones </a></li>
		<li><a href="#" id="ReportesPlazosFijosLoadServlet"
			class="servlet">Plazos Fijos</a></li>
		<li><a href="#" id="ReportesServiciosPagadosLoadServlet"
			class="servlet">Historial de Servicios Pagados</a></li>
		<li><a href="#" id="transferencias" class="lnk">Transferencias</a></li>
	</ul>
</form>

<script>
	$(".lnk").click(
			function() {
				$("#dir").val("/reportes/" + $(this).attr('id'));
				SubmitForm(document.forms["formservicios"],
						"/ramonbank/ComboLoadServlet");
			});
	$(".servlet").click(
			function() {
				SubmitForm(document.forms["formservicios"], "/ramonbank/"
						+ $(this).attr('id'));
			});
</script>
<jsp:include page="/footer.jsp"></jsp:include>