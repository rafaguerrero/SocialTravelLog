<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="tags" uri="http://dev.stl.com/jsp/stl/tags" %>

<c:set var="user" value="${pageContext.request.userPrincipal}" scope="page"/>

<div class="topUiComponent__login">
    <c:choose>
        <c:when test="${empty user}">
            <form:form action="/register" method="get" class="login__form">
                <input type="submit" value="Register" class="btn btn-sm login__btn login__register"/>
            </form:form>

            <form:form action="/login" method="post" class="login__form">
                <input type="submit" value="Login" class="btn btn-sm login__btn login__login"/>
            </form:form>
        </c:when>

        <c:otherwise>
            <div class="stl-user">
                <tags:travelerData username="${user.name}">
                    ${traveler.name}
                </tags:travelerData>
            </div>

            <form:form action="/logout" method="post" class="login__form">
                <input type="submit" value="Log out" class="btn btn-primary login__btn login__logout"/>
            </form:form>
        </c:otherwise>
    </c:choose>
</div>