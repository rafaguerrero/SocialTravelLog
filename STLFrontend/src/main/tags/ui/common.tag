<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>

<div class="ui">
    <div class="uiComponent topUiComponent">
        <img class="topUiComponent__logo" src="/statics/images/logo.png"/>

        <div class="topUiComponent__search"></div>

        <ui:login />
    </div>

    <div class="uiComponent leftUiComponent">
        <ul class="leftUiComponent__shortcuts">
            <li>Tags</li>
            <li>Authors</li>
            <li>Search</li>
        </ul>
    </div>

    <div class="uiComponent rightUiComponent">
        <div class="rightUiComponent__ad"></div>
    </div>
</div>