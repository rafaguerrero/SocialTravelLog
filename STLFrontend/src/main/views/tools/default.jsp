<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="structure" tagdir="/WEB-INF/tags/structure" %>

<structure:body className="tools">
    <h1>${toolName}</h1>

    <c:if test="${not empty tagName}">
        <h2>${tagName}</h2>
    </c:if>
</structure:body>