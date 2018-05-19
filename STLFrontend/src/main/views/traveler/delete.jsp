<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="structure" tagdir="/WEB-INF/tags/structure" %>

<structure:body className="traveler traveler--delete" title="STL - Traveler Creation">
    <form:form method="post" class="delete__form" modelAttribute="traveler">
        <fieldset>
            <div class="creation__form__row">
                <span>Are you sure you want to delete your account</span><br/>
                <input type="hidden" name="user" vale="${user}" />
                <input type="submit" value="Yes" />
            </div>
        </fieldset>
    </form:form>
</structure:body>