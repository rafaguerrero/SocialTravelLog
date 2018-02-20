<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="structure" tagdir="/WEB-INF/tags/structure" %>

<structure:body className="author author--page" title="STL - ${author.name}">
    <h1>${author.name}</h1>

    <h2>Articles</h2>
    <hr/>

    <c:forEach items="${articles}" var="article">
        <div class="author__article">
            <h3>${article.title}</h3>
            <h4>${article.url}</h4>
            <div>${article.body}</div>
        </div>
    </c:forEach>
</structure:body>