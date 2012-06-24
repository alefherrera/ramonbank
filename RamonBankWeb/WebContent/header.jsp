<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="/ramonbank/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/ramonbank/jquery.validate.min.js"></script>
<script type="text/javascript">
	jQuery
			.extend(
					jQuery.validator.messages,
					{
						required : "Campo Requerido",
						email : "Inserte un mail correcto",
						url : "Please enter a valid URL.",
						date : "Please enter a valid date.",
						number : "Inserte numeros",
						maxlength : jQuery.validator
								.format("Ingrese mas de {0} caracteres"),
						minlength : jQuery.validator
								.format("Ingrese al menos {0} caracteres"),
						rangelength : jQuery.validator
								.format("Please enter a value between {0} and {1} characters long."),
						range : jQuery.validator
								.format("Please enter a value between {0} and {1}."),
						max : jQuery.validator
								.format("Please enter a value less than or equal to {0}."),
						min : jQuery.validator
								.format("Please enter a value greater than or equal to {0}.")
					});
</script>
</head>
<body>


	<c:choose>
		<c:when test="${sessionScope.cliente == null }">
			<td><a href="/ramonbank/login/login.jsp">Login</a></td>
			<td><a href="/ramonbank/registro/registro.jsp">Registro</a></td>

		</c:when>
		<c:otherwise>
			<td>Cliente: ${sessionScope.cliente.get_dni()}</td>

			<td>Nombre: ${sessionScope.cliente.get_nombre()}</td>
			<td><a href="#" id="lnkLogOut">Salir</a></td>
		</c:otherwise>
	</c:choose>
	<form method="post" action="/ramonbank/LogOutServlet" id="formlogout"></form>
	<script>
		$("#lnkLogOut").click(function() {
			document.forms["formlogout"].submit();
		});
	</script>