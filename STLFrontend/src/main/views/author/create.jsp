<%@taglib prefix="interface" tagdir="/WEB-INF/tags/interface" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<interface:body className="author author--creation">
    <form class="stl-form-post" method="post">
        UserId:<br>
        <input type="text" name="userId" value="UserId"><br>

        Name:<br>
        <input type="text" name="name" value="Name"><br><br>

        <input type="submit" value="Submit">
    </form>
</interface:body>