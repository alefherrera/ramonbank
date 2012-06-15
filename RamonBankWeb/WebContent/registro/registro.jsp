<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="/ramonbank/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/ramonbank/jquery.validate.min.js"></script>
<script type="text/javascript">jQuery.extend(jQuery.validator.messages, {
    required: "Campo Requerido",    
    email: "Inserte un mail correcto",
    url: "Please enter a valid URL.",
    date: "Please enter a valid date.",    
    number: "Inserte numeros",
    maxlength: jQuery.validator.format("Ingrese mas de {0} caracteres"),
    minlength: jQuery.validator.format("Ingrese al menos {0} caracteres"),
    rangelength: jQuery.validator.format("Please enter a value between {0} and {1} characters long."),
    range: jQuery.validator.format("Please enter a value between {0} and {1}."),
    max: jQuery.validator.format("Please enter a value less than or equal to {0}."),
    min: jQuery.validator.format("Please enter a value greater than or equal to {0}.")
});</script>
</head>
<body>
	<jsp:useBean id="msjBean" scope="request" class="scope.MessageBean"></jsp:useBean>
	<form action="/ramonbank/registro" method="post">
		<table>
			<tbody>
				<tr>
					<td>DNI:</td>
					<td><input type="text" name="dni" class="required number minlength" minlength="8"  maxlength="8" /></td>
				</tr>
				<tr>
					<td>Nombre:</td>
					<td><input type="text" name="nombre" class="required" /></td>
				</tr>
				<tr>
					<td>Apellido:</td>
					<td><input type="text" name="apellido" class="required" /></td>
				</tr>
				<tr>
					<td>Direcicon:</td>
					<td><input type="text" name="direccion" class="required" /></td>
				</tr>
				<tr>
					<td>Mail:</td>
					<td><input type="text" name="email" class="required email" /></td>
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
	<script type="text/javascript">$(document).ready(function () {$("form").validate();});</script>
</body>
</html>