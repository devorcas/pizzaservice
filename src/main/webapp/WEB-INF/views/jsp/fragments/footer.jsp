<%--
  Created by IntelliJ IDEA.
  User: laguna
  Date: 25.05.2016
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="container">
    <hr>
    <footer>
        <p>&copy; Pavlo Velykyi 2017</p>
    </footer>
</div>

<script
        src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

<spring:url value="/resources/core/js/hello.js" var="coreJs" />
<spring:url value="/resources/core/js/bootstrap.min.js"
            var="bootstrapJs" />

<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>


