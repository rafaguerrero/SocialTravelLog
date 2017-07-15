<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en-US">
    <body>
        <jsp:include page="../interface/menu.jsp"/>

        <form action="/hub/article/save" method="post">
            UserId:<br>
            <input type="text" name="userId" value="UserId"><br>

            Name:<br>
            <input type="text" name="name" value="Name"><br><br>

            <input type="submit" value="Submit">
        </form>
    </body>
</html>