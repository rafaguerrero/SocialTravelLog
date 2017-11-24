<%@taglib prefix="interface" tagdir="/WEB-INF/tags/interface" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<interface:body className="articleCreation">
    <form method="post" class="stl-form-post">
        Title:<br>
        <input type="text" name="title" value="Title"><br>

        Body:<br>
        <input type="text" name="body" value="Body"><br><br>

        <input type="submit" value="Submit">
    </form>
</interface:body>