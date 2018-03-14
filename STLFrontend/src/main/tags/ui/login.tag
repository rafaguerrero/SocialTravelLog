<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="user" value="${pageContext.request.userPrincipal}" scope="page"/>

<div class="topUiComponent__login">
    <c:choose>
        <c:when test="${empty user}">
            <form:form action="/login" method="post">
                <input type="submit" value="Login" />
            </form:form>
        </c:when>

        <c:otherwise>
            <div class="stl-user">
                ${user.name}
            </div>

            <form:form action="/logout" method="post">
                <input type="submit" value="Log out" />
            </form:form>
        </c:otherwise>
    </c:choose>
</div>


