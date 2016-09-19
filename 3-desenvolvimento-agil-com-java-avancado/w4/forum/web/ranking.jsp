<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Ranking</title>
</head>
<body>
    <c:if test="${!usuarios.isEmpty()}">
        <ol>
            <c:forEach items="${usuarios}" var="usuario">
                <li>
                    ${usuario.getLogin()}: ${usuario.getPontos()}
                </li>
            </c:forEach>
        </ol>
    </c:if>
</body>
</html>
