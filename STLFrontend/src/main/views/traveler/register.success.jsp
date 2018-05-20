<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="structure" tagdir="/WEB-INF/tags/structure" %>

<structure:body
        className="traveler traveler--creation traveler--creation--success"
        title="Welcome ${traveler.name}!">

    <h1>Welcome!!</h1>
    <h2>${traveler.name}</h2>

</structure:body>