<%@taglib prefix="interface" tagdir="/WEB-INF/tags/interface" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<interface:body className="homepage">
    <c:forEach items="${articles}" var="article">
        <div class="homepage__article article">
            <h1>${article.title}</h1>
            <div class="article__metadata">
                <div class="article__metadata__author">
                    By <a href="/hub/author/${article.author.userId}" target="_self">${article.author.name}</a>
                </div>
                <div class="article__metadata__author">
                    Tags:
                    <c:forEach items="${article.tags}" var="tag">
                        <span>${tag}</span>
                    </c:forEach>
                </div>
            </div>
        </div>
    </c:forEach>
</interface:body>