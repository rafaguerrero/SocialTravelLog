<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="structure" tagdir="/WEB-INF/tags/structure" %>
<%@taglib prefix="trips" tagdir="/WEB-INF/tags/trips" %>

<structure:body className="homepage">
    <c:forEach items="${trips}" var="trip">
        <trips:render trip="${trip}"/>
    </c:forEach>
</structure:body>