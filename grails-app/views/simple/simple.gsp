<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Forgotten Past</title>
</head>

<body>
<g:link controller="states">States</g:link>
<g:each in="${states}" var="state">
    <p>${state.name}</p>
</g:each>
</body>
</html>