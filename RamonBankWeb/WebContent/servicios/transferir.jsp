<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:include page="/header.jsp"></jsp:include>
<form method="post" action="/ramonbank/TransferirServlet">
	Monto:<input type="text" id="Monto" name="Monto" />
	Cuenta Destino:<input type="text" id="idCuentaHasta" name="idCuentaHasta" />
	<jsp:include page="/combocuenta.jsp"></jsp:include>
	<input type="submit" value="Transferir">
</form>
<jsp:include page="/footer.jsp"></jsp:include>