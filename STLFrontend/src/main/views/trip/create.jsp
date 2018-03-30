<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="structure" tagdir="/WEB-INF/tags/structure" %>

<structure:body className="article article--creation" title="STL - Article Creation">
    <form:form method="post" class="creation__form" modelAttribute="trip">
        <div class="creation__form__row">
            <span>Title:</span>
            <input type="text" name="title" value="Title">
        </div>
        <div class="creation__form__row">
            <span>Body:</span>
            <input type="text" name="body" value="Body">
        </div>
        <div class="creation__form__row">
            <input type="submit" value="Submit">
        </div>
    </form:form>
</structure:body>