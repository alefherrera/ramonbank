<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${msjBean != null }">
	<br>
	<p style="color: red;">
		<c:out value="${msjBean.mensaje }"></c:out>
	</p>
</c:if>
<script type="text/javascript">
	$(document).ready(function() {
		$("form").validate();
	});
</script>
<div id="footer">
	<p>&copy; Sitename.com. All rights reserved. Design by <a href="http://www.freecsstemplates.org">FCT</a>. Photos by <a href="http://fotogrph.com/">Fotogrph</a>.</p>
</div>
</body>
</html>