<%@taglib prefix="interface" tagdir="/WEB-INF/tags/interface" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<interface:body className="article article--page">
    <h1>${article.title}</h1>

    <div class="article__metadata">
        <div class="article__metadata__author">
            By <a href="/author/${article.author.userId}" target="_self">${article.author.name}</a>
        </div>
        <div class="article__metadata__author">
            Tags:
            <c:forEach items="${article.tags}" var="tag">
                <span>${tag}</span>
            </c:forEach>
        </div>
    </div>

    <div>${article.body}</div>
</interface:body>