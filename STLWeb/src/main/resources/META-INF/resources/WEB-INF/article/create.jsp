<%@taglib prefix="interface" tagdir="/WEB-INF/tags/interface" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<interface:body className="articleCreation">
    <div class="articleCreation__main">
        <form method="post" class="articleCreation__form">
            Title:<br>
            <input type="text" name="title" value="Title" class="articleCreation__form__title"><br>

            Author:<br>
            <input type="text" name="author" value="Author" class="articleCreation__form__author"><br>

            Body:<br>
            <input type="text" name="body" value="Body" class="articleCreation__form__body"><br><br>

            <input type="submit" value="Submit">
        </form>
    </div>

    <div class="articleCreation__ok hide"><h1>OK</h1></div>
    <div class="articleCreation__error hide"><h1>ERROR</h1></div>

</interface:body>