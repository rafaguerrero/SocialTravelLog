<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="structure" tagdir="/WEB-INF/tags/structure" %>

<structure:body className="tools">
    <h1>${toolName}</h1>

    <c:if test="${not empty tagName}">
        <h2>${tagName}</h2>
    </c:if>
    <c:choose>
        <c:when test="${not empty trips}">
            <c:forEach items="${trips}" var="trip">
                <div class="taveler__trip">
                    <h3>${trip.title}</h3>
                    <h4>${trip.url}</h4>
                    <div>${trip.body}</div>
                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <h2>Esto esta un poco vacio...</h2>
        </c:otherwise>
    </c:choose>
</structure:body>