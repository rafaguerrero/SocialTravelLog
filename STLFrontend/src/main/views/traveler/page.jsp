<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="structure" tagdir="/WEB-INF/tags/structure" %>
<%@taglib prefix="trips" tagdir="/WEB-INF/tags/trips" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>

<c:set var="user" value="${pageContext.request.userPrincipal}" scope="page"/>

<structure:body className="traveler traveler--page" title="STL - ${traveler.name}">

    <ui:follow/>

    <h1>${traveler.name}</h1>

    <h2>Trips</h2>
    <hr/>

    <c:forEach items="${trips}" var="trip">
        <trips:render trip="${trip}"/>
    </c:forEach>
</structure:body>