<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="structure" tagdir="/WEB-INF/tags/structure" %>

<structure:body className="article article--page" title="STL - ${article.title}">
    <h1>${article.title}</h1>

    <div class="article__metadata">
        <div class="article__metadata__author">
            By <a href="/author/${article.traveler.username}" target="_self">${article.traveler.name}</a>
        </div>
        <div class="article__metadata__author">
            Tags:
            <c:forEach items="${article.tags}" var="tag">
                <span>${tag}</span>
            </c:forEach>
        </div>
    </div>

    <div>${article.body}</div>
</structure:body>