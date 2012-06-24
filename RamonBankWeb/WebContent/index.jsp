<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/header.jsp"></jsp:include>
<table>
	<tr>

		<c:if test="${sessionScope.cliente != null }">
			<ul>
				<li><a href="#" id="lnkCuentas">Cuentas</a></li>
				<li>Jorge</li>
				<li>Alberto</li>
			</ul>
		</c:if>

	</tr>
</table>
<form method="post" action="/ramonbank/CuentasServlet" id="formcuentas"></form>
<script>$("#lnkCuentas").click(function(){ document.forms["formcuentas"].submit(); });</script>
<form method="post" id="formredir"></form>
<script>
	$("#lnkCuentas").click(function() {
		document.forms["formredir"].action = "/ramonbank/CuentasServlet";
		document.forms["formredir"].submit();
	});
	$("#lnkServicios").click(function() {
		document.forms["formredir"].action = "/ramonbank/ServiciosLoadServlet";
		document.forms["formredir"].submit();
	});
</script>
<jsp:include page="/footer.jsp"></jsp:include>
