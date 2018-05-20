
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@attribute name="trip" required="true" type="com.stl.entity.Trip"%>

<c:set var="traveler" value="${trip.traveler}" scope="page"/>

<div class="trip__box">
    <h1><a href="/stl${trip.url}" target="_self">${trip.title}</a></h1>

    <div class="trip__metadata">
        <div class="trip__metadata__traveler">
            By <a href="/stl/${traveler.username}" target="_self">${traveler.name}</a>
        </div>

        <div class="trip__metadata__tags">
            Tags:
            <c:forEach items="${trip.tags}" var="tag">
                <span><a href="/tag/${tag}" target="_self">${tag}</a></span>
            </c:forEach>
        </div>
    </div>
</div>