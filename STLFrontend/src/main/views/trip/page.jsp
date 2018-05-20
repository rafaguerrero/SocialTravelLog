<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="structure" tagdir="/WEB-INF/tags/structure" %>

<structure:body className="trip trip--page" title="STL - ${trip.title}">
    <h1>${trip.title}</h1>

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