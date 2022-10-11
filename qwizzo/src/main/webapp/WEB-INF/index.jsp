<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Qwizzo</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/style.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>

<h1>Welcome to Qwizzo!</h1>

<div class= "regi">
<form:form action="/register" method="post" modelAttribute="newPlayer" class="col-sm">
<h3>Register</h3>
<div >
<form:label path="playerName">Player Name:</form:label>
<form:input path="playerName"/>
<form:errors path="playerName"/>
</div>
<br>
<div>
<form:label path="email">Email:</form:label>
<form:input path="email"/>
<form:errors path="email"/>
</div>
<br>
<div>
<form:label path="password">Password:</form:label>
<form:input path="password" type="password"/>
<form:errors path="password"/>
</div>
<br>
<div>
<form:label path="confirmPassword">Confirm Password:</form:label>
<form:input path="confirmPassword" type="password"/>
<form:errors path="confirmPassword"/>
</div>
<br>
<button>Register!</button>

</form:form>


<div class="log">
<form:form action="/login" method="post" modelAttribute="userLogin" class="col-sm">
<h3>Login</h3>
<div>
<form:label path="email">Email</form:label>
<form:input path="email"/>
<form:errors path="email"/>
</div>
<br>
<div>
<form:label path="password">Password</form:label>
<form:input path="password" type="password"/>
<form:errors path="password"/>
</div>
<br>
<button>Login</button>
</form:form> 

</div>
</div>
</body>
</html>