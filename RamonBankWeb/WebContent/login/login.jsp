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
<jsp:useBean id="msjBean" scope="request" class="scope.MessageBean"></jsp:useBean>
	<form action="/ramonbank/login" method="post">
			<table>
				<tbody>
					<tr>
						<td>DNI:</td>
						<td><input type="text" name="dni" /></td>
					</tr>
					<tr>
						<td><input type="submit" value="Entrar"></input></td>
					</tr>
				</tbody>
			</table>
		</form>

		<c:if test="${msjBean != null }">
		<br>
		<p style="color: red;">
			<c:out value="${msjBean.mensaje }"></c:out>
		</p>
	</c:if>

</body>
</html>