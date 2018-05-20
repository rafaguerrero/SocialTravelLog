
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@attribute name="title" required="false" type="java.lang.String" %>

<c:if test="${empty title}">
    <c:set var="title" value="SocialTravelLog"/>
</c:if>

<head>
    <title>${title}</title>
    <script src="/statics/main.js" async></script>
    <style>
        body {
            display: none;
        }
    </style>
</head>