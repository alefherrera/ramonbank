<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/header.jsp"></jsp:include>
	<jsp:useBean id="msjBean" scope="request" class="scope.MessageBean"></jsp:useBean>
	<form action="/ramonbank/RegistroServlet" method="post">
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
<jsp:include page="/footer.jsp"></jsp:include>