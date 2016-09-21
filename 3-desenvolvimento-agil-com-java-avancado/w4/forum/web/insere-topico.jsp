<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Criar novo tópico</title>
</head>
<body>
    <form action="/inserirTopico", method="post">
        Título: <input type="text" name="titulo" />
        Conteúdo: <textarea name="conteudo" rows="4" cols="120"></textarea>
        <input type="submit" name="Criar" />
    </form>
</body>
</html>
