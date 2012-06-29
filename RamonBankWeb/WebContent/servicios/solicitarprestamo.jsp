<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/header.jsp"></jsp:include>
<form action="/ramonbank/SolicitarPrestamoServlet" method="post">
	Descubierto: <input type="checkbox" id="Descubierto" name="Descubierto" />
	Monto: <input type="text" id="cantPrestamo" name="cantPrestamo" />
	Cantidad de Cuotas:<input type="text" id="cantCuotas" name="cantCuotas" />
	<jsp:include page="/combocuenta.jsp"></jsp:include>
	<input type="submit" value="Solicitar">


</form>
<script type="text/javascript">
	$(document).ready(function () { $("#idCuenta").append("<option value='0'>Efectivo</option>"); });
</script>
<jsp:include page="/footer.jsp"></jsp:include>