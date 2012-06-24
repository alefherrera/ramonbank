<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/header.jsp"></jsp:include>
<jsp:useBean id="CuentasBean" scope="request" type="scope.CuentasBean"></jsp:useBean>
<jsp:useBean id="msjBean" scope="request" class="scope.MessageBean"></jsp:useBean>

<form method="post" action="/ramonbank/CrearCuentaServlet">
	<select name="comboTipoCuenta">

		<c:forEach items="${ CuentasBean.getTipos() }" var="tipo">
			<option value="${ tipo.id() }">
				<c:out value="${ tipo.nombre() }"></c:out>
			</option>
		</c:forEach>

	</select> <input type="submit" value="Crear">
</form>
<c:if test="${msjBean != null }">
	<br>
	<p style="color: red;">
		<c:out value="${msjBean.mensaje }"></c:out>
	</p>
</c:if>
<jsp:include page="/footer.jsp"></jsp:include>