<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="structure" tagdir="/WEB-INF/tags/structure" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>

<structure:body className="trip trip--page" title="STL - ${trip.title}">
    <h1>${trip.title}</h1>

    <ui:rating/>

    <c:if test="${not empty pageContext.request.userPrincipal}">
        <form:form method="get" action="/access${trip.url}" class="access__form">
            <span>Grand access to a friend to write this trip with you</span>

            <div class="creation__form__row">
                <span>Traveler:</span>
                <input type="text" name="travelerUsername" value="Username">
            </div>
            <div class="creation__form__row">
                <input type="submit" value="Submit">
            </div>
        </form:form>
    </c:if>

    <div class="trip__metadata">
        <div class="trip__metadata__traveler">
            By <a href="/stl/${traveler.username}" target="_self">${traveler.name}</a>
        </div>
        <div class="trip__metadata__tags">
            Tags:
            <c:forEach items="${trip.tags}" var="tag">
                <span><a href="/tag/${tag}" target="_self">${tag}</a></span>
            </c:forEach>
        </div>
    </div>

    <div>${trip.body}</div>
</structure:body>