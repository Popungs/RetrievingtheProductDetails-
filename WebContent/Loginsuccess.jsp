<%@ page import="com.model.Login" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome!</title>
</head>
<body>
Welcome! : <%

Login login = (Login) session.getAttribute("sesname");
out.println(login);

%> <br/>

<a href ="Logout">logout</a>

</body>
</html>