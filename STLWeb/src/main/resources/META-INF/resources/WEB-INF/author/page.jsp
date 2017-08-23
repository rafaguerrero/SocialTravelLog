<%@taglib prefix="interface" tagdir="/WEB-INF/tags/interface" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<interface:body className="author author--page">
    <h1>${author.name}</h1>

    <h2>Articles</h2>

    <c:forEach items="${articles}" var="article">
        <hr>
        <h3>${article.title}</h3>
        <h4>${article.url}</h4>
        <div>${article.body}</div>
    </c:forEach>

</interface:body>