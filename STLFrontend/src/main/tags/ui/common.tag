<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="user" value="${pageContext.request.userPrincipal}" scope="page"/>

<div class="ui">
    <div class="uiComponent topUiComponent">
        <c:import url="/WEB-INF/views/interface/header/default.jsp"/>
    </div>

    <div class="uiComponent leftUiComponent">
        <ul class="leftUiComponent__shortcuts">
            <li>Tags</li>
            <li>Authors</li>
            <li>Search</li>

            <c:if test="${not empty user}">
                <li><a href="/${user.name}/new_trip">Create a new trip</a></li>
                <li><a href="/${user.name}/delete">Delete account</a></li>
            </c:if>
        </ul>
    </div>

    <div class="uiComponent rightUiComponent">
        <div class="rightUiComponent__ad"></div>
    </div>
</div>