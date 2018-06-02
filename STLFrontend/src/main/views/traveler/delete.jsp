<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="structure" tagdir="/WEB-INF/tags/structure" %>

<structure:body className="traveler traveler--delete" title="STL - Traveler Creation">
    <form:form method="POST" class="delete__form">
        <fieldset>
            <div class="creation__form__row">
                <span>Are you sure you want to delete your account</span><br/>
            </div>
            <div class="creation__form__row">
                <input type='checkbox' name='deleteArticles'/>
                <span>I want to delete all my articles too</span><br/>
            </div>
            <div class="creation__form__row">
                <input type="submit" value="Yes" />
            </div>
        </fieldset>
    </form:form>
</structure:body>