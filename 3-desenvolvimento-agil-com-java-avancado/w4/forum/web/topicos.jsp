<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Topicos</title>
</head>
    <c:if test="${!topicos.isEmpty()}">
        <body>
            <table>
                <thead>
                    Tópico
                </thead>
                <thead>
                    Autor
                </thead>
                <c:forEach items="${topicos}" var="topico">
                    <tr>
                        <td>
                            ${topico.getLogin()}
                        </td>
                        <td>
                            <a href="/exibirTopico?id=${topico.getId()}">${topico.getTitulo()}</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </body>
    </c:if>

    <a href="/exibirRanking">Exibir ranking</a>
    <a href="/insere-topico.jsp">Criar um novo tópico</a>
</html>
