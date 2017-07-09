<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>${author.name}</h1>

<h2>Articles</h2>
<c:forEach items="${articles}" var="article">
    <hr>
    <h3>${article.title}</h3>
    <div>${article.body}</div>
</c:forEach>