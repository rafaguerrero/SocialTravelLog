<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="topUiComponent__search">
    <form:form action="/search" method="get" class="topUiComponent__search__form">
        <input type="text" placeholder="Search" name="q"
               class="form-control form-control-sm topUiComponent__search__input"/>
    </form:form>
</div>
