<%--
  ~ Copyright (c) 2013 by Marfeel Solutions (http://www.marfeel.com)
  ~ All Rights Reserved.
  ~
  ~ NOTICE:  All information contained herein is, and remains
  ~ the property of Marfeel Solutions S.L and its suppliers,
  ~ if any.  The intellectual and technical concepts contained
  ~ herein are proprietary to Marfeel Solutions S.L and its
  ~ suppliers and are protected by trade secret or copyright law.
  ~ Dissemination of this information or reproduction of this material
  ~ is strictly forbidden unless prior written permission is obtained
  ~ from Marfeel Solutions SL.
  --%>

<%@taglib prefix="interface" tagdir="/WEB-INF/tags/interface" %>
<%@attribute name="className" required="true" type="java.lang.String"%>

<!DOCTYPE html>
<html lang="en-US">
    <head>
        <script src="/js/main.js" async></script>
        <link href="/css/main.css" rel="stylesheet"/>


        <link href="/css/homepage.css" rel="stylesheet"/>


    </head>

    <body class="${className}">
        <interface:menu/>

        <div class="content">
            <jsp:doBody />
        </div>
    </body>
</html>