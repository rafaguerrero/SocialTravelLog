<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="structure" tagdir="/WEB-INF/tags/structure" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<structure:body className="logged" title="Admin STL">
    <h1>Hello Admin!</h1>

    <form:form action="/logout" method="post">
        <input type="submit"
               value="Log out" />
    </form:form>
</structure:body>