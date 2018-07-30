<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="structure" tagdir="/WEB-INF/tags/structure" %>

<structure:body className="trip trip--creation" title="STL - Trip Creation">
    <c:choose>
        <c:when test="${errorStatus == 'tripAlreadyExists'}">
            <div class="author__creation__error">Trip already exists</div>
        </c:when>
    </c:choose>

    <form:form method="post" class="creation__form" modelAttribute="trip">
        <div class="creation__form__row">
            <span>Title:</span>
            <input type="text" name="title" value="${not empty trip.title ? trip.title : 'Title'}">
        </div>
        <div class="creation__form__row">
            <span>Body:</span>
            <input type="text" name="body" value="${not empty trip.body ? trip.body : 'Body'}">
        </div>
        <div class="creation__form__row">
            <span>Tags:</span>
            <input type="text" name="tags" value="${not empty trip.tags ? trip.tags : 'Tags'}">
        </div>
        <div class="creation__form__row">
            <input type="submit" value="Submit">
        </div>
    </form:form>
</structure:body>