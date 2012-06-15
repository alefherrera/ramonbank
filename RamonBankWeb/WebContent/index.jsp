<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>

			<c:choose>
				<c:when test="${sessionScope.cliente == null }">
					<td><a href="/ramonbank/login/login.jsp">Login</a></td>
					<td><a href="/ramonbank/registro/registro.jsp">Registro</a></td>
				</c:when>
				<c:otherwise>
					<td>Cliente: ${sessionScope.cliente.get_dni()}</td>

					<td>Nombre: ${sessionScope.cliente.get_nombre()}</td>
					<td><a href="logout">Salir</a></td>
				</c:otherwise>
			</c:choose>

		</tr>
	</table>


</body>
</html>