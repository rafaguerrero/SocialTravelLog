<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en-US">
<body>
    <jsp:include page="../interface/menu.jsp"/>

    <form action="/hub/article/save" method="post">
        Title:<br>
        <input type="text" name="title" value="Title"><br>

        Body:<br>
        <input type="text" name="body" value="Body"><br><br>

        <input type="submit" value="Submit">
    </form>
</body>
</html>