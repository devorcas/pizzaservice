<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<jsp:include page="../fragments/header.jsp"/>

<body>
<div align="center">
    <h2>All pizzas in DB:</h2>
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
    <sec:authorize access="hasRole('USER')">
        This content will only be visible to users who have the "supervisor" authority in their list of <tt>GrantedAuthority</tt>s.
    </sec:authorize>

    <table border="0">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Type</th>
        </tr>
        <c:if test="${not empty pizzas}">
        <c:forEach var="pizza" items="${pizzas}">
            <tr>
                <td>${pizza.id}</td>
                <td>${pizza.name}</td>
                <td>${pizza.price}</td>
                <td>${pizza.type}</td>
                <td>
                    <form name="remove" action="./remove" method="post">
                        <input type="hidden" value="${pizza.id}" name="id"/>
                        <input type="submit"
                               name="submit"
                               value="remove">
                    </form>
                </td>
                <td>
                    <form name="edit" action="./edit" method="post">
                        <input type="hidden" value="${pizza.id}" name="id"/>
                        <input type="submit"
                               name="submit"
                               value="edit">
                    </form>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <form name="add" action="./add" method="post">
                <td></td>
                <td><input type="text" name="name"/></td>
                <td><input type="text" name="price"/></td>
                <td><select name="type">
                    <option value=null></option>
                    <option value="MEAT">MEAT</option>
                    <option value="SEA">SEA</option>
                    <option value="VEGETERIAN">VEGETERIAN</option>
                </select></td>
                <td>
                    <input type="submit"
                           name="submit"
                           value="add pizza">
                </td>
            </form>
        </tr>
    </table>
    </c:if>

</div>
<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>