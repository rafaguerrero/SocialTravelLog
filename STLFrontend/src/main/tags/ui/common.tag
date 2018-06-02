<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="user" value="${pageContext.request.userPrincipal}" scope="page"/>

<div class="ui">
    <div class="uiComponent topUiComponent">
        <c:import url="/WEB-INF/views/interface/header/default.jsp"/>
    </div>

    <div class="uiComponent leftUiComponent">
        <ul class="leftUiComponent__shortcuts">
            <li><a href="/?lateral=explore">Explore</a></li>
            <li>Tags</li>
            <li>Search</li>

            <c:if test="${not empty user}">
                <li><a href="/following/${user.name}">Fellow Travelers</a></li>
                <li><a href="/trip/${user.name}">Create a new trip</a></li>
                <li><a href="/d/${user.name}">Delete account</a></li>
            </c:if>
        </ul>
    </div>

    <div class="uiComponent rightUiComponent">
        <div class="rightUiComponent__ad">
            Ad
        </div>
    </div>
</div>