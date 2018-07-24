<%@taglib prefix="structure" tagdir="/WEB-INF/tags/structure" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>

<%@attribute name="title" required="false" type="java.lang.String" %>
<%@attribute name="className" required="false" type="java.lang.String" %>

<html lang="en-US">
    <structure:header title="${title}"/>

    <body class="${className}">
        <ui:common />

        <div class="content" data-component-name="testComponent">
            <jsp:doBody />
        </div>

        <script>
            Vue.component('today', {
                mounted: function() {
                    console.log("----------------");
                    console.log("Hola que ase");
                    console.log("----------------");
                }
            });

            new Vue({
                el: '.content',
                data: {
                    txt : "Inline Component"
                }
            })

            Vue.component('todo-item', {
                template: '<span>This is a list done with Vue</span>'
            })

            new Vue({
                el: '#ui',
                data: {}
            })
        </script>
    </body>
</html>