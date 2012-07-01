<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="style.css" rel="stylesheet" type="text/css" media="screen" />
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
<img alt="" src="http://i.imgur.com/R9qK6.png"><br/>
<jsp:useBean id="msjBean" scope="request" class="scope.MessageBean"></jsp:useBean>
	<c:choose>
		<c:when test="${sessionScope.cliente == null }">
			<a href="/ramonbank/login/login.jsp" id="lnklogin">Login</a>
			<a href="/ramonbank/registro/registro.jsp">Registro</a>
		</c:when>
		<c:otherwise>
			Cliente: ${sessionScope.cliente.get_dni()}
			Nombre: ${sessionScope.cliente.get_nombre()}
			<a href="#" id="lnkLogOut">Salir</a>
		</c:otherwise>
	</c:choose>
	<a href="/ramonbank/index.jsp"> HOME </a>
	<script>
		function CrearForm(accion) {
			var form = document.createElement("form");
			form.method = 'post';
			SubmitForm(form,accion);
		}
		function SubmitForm(form,accion)
		{
			form.action = accion;
			form.submit();
		}
		
		$("#lnkLogOut").click(function() {
			CrearForm("/ramonbank/LogOutServlet");
		});
	</script>