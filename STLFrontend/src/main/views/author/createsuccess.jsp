<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="structure" tagdir="/WEB-INF/tags/structure" %>

<structure:body className="author author--page" title="Welcome ${author.name}!">
    <h1>Welcome!!</h1>
    <h2 th:text="${author.name}"></h2>
</structure:body>