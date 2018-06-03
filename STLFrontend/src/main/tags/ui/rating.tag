
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="tags" uri="http://dev.stl.com/jsp/stl/tags" %>

<c:set var="user" value="${pageContext.request.userPrincipal}" scope="page"/>

<div class="rating">
    <h4>${trip.rating.getAverage()} Stars - (Total ${trip.rating.total})</h4>
</div>


<c:if test="${not empty user && !user.name.equals(traveler.username)}">
    <hr>

    <tags:travelerData username="${user.name}">
        <c:set var="travelersRating" value="${traveler.rated[trip.id]}" scope="page"/>

        <c:choose>
            <c:when test="${empty travelersRating}">
                <form:form action="/rate${trip.url}" method="post" class="rate__form">
                    <input type="radio" name="stars" value="ONE"> 1<br>
                    <input type="radio" name="stars" value="TWO"> 2<br>
                    <input type="radio" name="stars" value="THREE" checked> 3<br>
                    <input type="radio" name="stars" value="FOUR"> 4<br>
                    <input type="radio" name="stars" value="FIVE"> 5<br>

                    <input type="submit" value="Rate" class="btn btn-sm rate__btn"/>
                </form:form>
            </c:when>
            <c:otherwise>
                <h5>Your rating was ${travelersRating}</h5>
            </c:otherwise>
        </c:choose>
    </tags:travelerData>

    <hr>
</c:if>