<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="ServiciosBean" scope="request" type="scope.ServiciosBean"></jsp:useBean>
Servicios:
<select name="idServicio" id="idServicio">

	<c:forEach items="${ ServiciosBean.getServicios() }" var="servicio">
		<option value="${ servicio.get_id() }">
			<c:out value="${ servicio.get_descripcion()} - ${servicio.get_monto()}"></c:out>
		</option>
	</c:forEach>

</select>