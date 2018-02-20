<%@taglib prefix="structure" tagdir="/WEB-INF/tags/structure" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>

<%@attribute name="title" required="false" type="java.lang.String" %>
<%@attribute name="className" required="false" type="java.lang.String" %>

<html lang="en-US">
    <structure:header title="${title}"/>

    <body class="${className}">
        <ui:common />

        <div class="content" data-component-name="testComponent">
            <jsp:doBody />
        </div>
    </body>
</html>