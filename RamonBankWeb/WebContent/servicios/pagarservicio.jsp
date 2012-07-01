<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:include page="/header.jsp"></jsp:include>
<form method="post" action="/ramonbank/PagarServicioServlet">
	<jsp:include page="/comboservicio.jsp"></jsp:include>
	<jsp:include page="/combocuenta.jsp"></jsp:include>
	<input type="submit" value="Pagar">
</form>
<jsp:include page="/footer.jsp"></jsp:include>