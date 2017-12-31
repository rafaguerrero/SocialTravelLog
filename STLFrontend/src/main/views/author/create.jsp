<%@taglib prefix="interface" tagdir="/WEB-INF/tags/interface" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<interface:body className="author author--creation">

    <!--<c:if test="${status=='error'}">This user already exists</c:if>-->


    <form:form method="POST" modelAttribute="author">
        UserId:<br>
        <form:input path="userId"/><br>

        Name:<br>
    <form:input path="name"/>


        <input type="submit" value="Submit">
    </form:form>

</interface:body>