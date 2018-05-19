<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="structure" tagdir="/WEB-INF/tags/structure" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<structure:body className="logged" title="Admin STL">
    <h3>Login with Username and Password</h3>

    <form:form name='loginForm' action='/j_spring_security_check' method='POST'>
        <c:choose>
            <c:when test="${param.error != null}">
                <p style="color: red;">Your login attempt was not successful</p>
            </c:when>
            <c:when test="${param.delete != null}">
                <p>You account has successfuly been deleted</p>
            </c:when>
            <c:when test="${param.logout != null}">
                <p>You successfuly logged out</p>
            </c:when>
        </c:choose>

        <table>
            <tr>
                <td>Username:</td>
                <td><input type='text' name='j_username'/></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type='password' name='j_password'/></td>
            </tr>
            <tr>
                <td colspan='2'><input name="submit" type="submit" value="Login"/></td>
            </tr>
            <tr>
                <td><input type='checkbox' name='_spring_security_remember_me'/></td>
                <td>Remember me on this computer.</td>
            </tr>
        </table>
    </form:form>
</structure:body>