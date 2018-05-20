<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="structure" tagdir="/WEB-INF/tags/structure" %>

<structure:body className="traveler traveler--creation" title="STL - Traveler Registration">
    <c:choose>
        <c:when test="${status == 'genericError'}">
            <div class="author__creation__error">There was an error</div>
        </c:when>
        <c:when test="${status == 'authorAlreadyExists'}">
            <div class="author__creation__error">User already exists</div>
        </c:when>
    </c:choose>

    <form:form method="post" class="creation__form" modelAttribute="traveler">
        <fieldset>
            <div class="creation__form__row">
                <span>Username:</span><br/>
                <form:input type="text" path="username"/>
            </div>
            <div class="creation__form__row">
                <span>Password:</span><br/>
                <form:input type="password" path="password"/>
            </div>
            <div class="creation__form__row">
                <span>Full name:</span><br/>
                <form:input type="text" path="name" />
            </div>
            <div class="creation__form__row">
                <input type="submit" value="Submit" />
            </div>
        </fieldset>
    </form:form>
</structure:body>