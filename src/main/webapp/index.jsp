<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="WEB-INF/views/jsp/fragments/header.jsp"/>
<body>
<div align="center">
 	<form action="./app/pizza/" method="get">
		<input type="hidden"
			   name="${_csrf.parameterName}"
			   value="${_csrf.token}"/>
 		<input type="submit" name="submit" value="Go to pizza CRUD">
 	</form>
</div>
<jsp:include page="WEB-INF/views/jsp/fragments/footer.jsp"/>
</body>
</html>