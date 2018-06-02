<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="tags" uri="http://dev.stl.com/jsp/stl/tags" %>

<c:set var="user" value="${pageContext.request.userPrincipal}" scope="page"/>

<c:if test="${not empty user && !user.name.equals(traveler.username)}">
    <c:set var="currentTravelerUsername" value="${traveler.username}" scope="page"/>

    <tags:travelerData username="${user.name}">
        <c:choose>
            <c:when test="${not traveler.following.contains(currentTravelerUsername)}">
                <form:form action="/follow/${currentTravelerUsername}" method="post" class="follow__form">
                    <input type="submit" value="Follow" class="btn btn-sm traveler__btn follow__btn"/>
                </form:form>
            </c:when>
            <c:otherwise>
                <form:form action="/unfollow/${currentTravelerUsername}" method="post" class="follow__form">
                    <input type="submit" value="Unfollow" class="btn btn-sm traveler__btn unfollow__btn"/>
                </form:form>
            </c:otherwise>
        </c:choose>
    </tags:travelerData>

    <hr>
</c:if>