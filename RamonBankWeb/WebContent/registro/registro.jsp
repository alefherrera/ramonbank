<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="../registroservlet" method="post">
<table>
			<tbody>
				<tr>
					<td>DNI:</td>
					<td><input type="text" name="dni" /></td>
				</tr>
				<tr>
					<td>Nombre:</td>
					<td><input type="text" name="nombre" /></td>
				</tr>
				<tr>
					<td>Apellido:</td>
					<td><input type="text" name="apellido" /></td>
				</tr>
				<tr>
					<td>Direcicon:</td>
					<td><input type="text" name="direccion" /></td>
				</tr>
				<tr>
					<td>Mail:</td>
					<td><input type="text" name="email" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="Entrar"></input></td>
				</tr>
			</tbody>
		</table>
</form>
</body>
</html>