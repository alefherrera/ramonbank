<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="/header.jsp"></jsp:include>
<form method="post" action="/ramonbank/DepositarServlet">
	Monto:<input type="text" id="Monto" name="Monto" />
	<jsp:include page="/combocuenta.jsp"></jsp:include>
	<input type="submit" value="Depositar">
</form>
<jsp:include page="/footer.jsp"></jsp:include>