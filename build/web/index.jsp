<%-- 
    Document   : index
    Created on : 02/05/2018, 12:52:56 AM
    Author     : Martín
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" type="image/icon" href="images/icono.ico" />
        <title>Inicio</title>
    </head>
    <body>
        <%-- Redireccionará al archivo listarLibros.jsp, pero en la 
        URL mostrará esta página como inicio --%>
        <jsp:forward page="listarLibros.jsp"></jsp:forward>
    </body>
</html>
