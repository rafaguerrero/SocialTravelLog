
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@attribute name="title" required="false" type="java.lang.String" %>

<c:if test="${empty title}">
    <c:set var="title" value="SocialTravelLog"/>
</c:if>

<head>
    <title>${title}</title>
    <script src="/statics/js/main.js" async></script>
</head>