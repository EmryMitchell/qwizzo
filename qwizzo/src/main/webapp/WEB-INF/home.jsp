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
<div class = "welcome">
<h2>
		Welcome,
		<c:out value="${player.playerName}"></c:out>
	</h2>
	<a href="/logout"><button>Log Out</button></a>
</div>

<div>
<table class="table table-striped" id="tbl">
<thead>
    <tr>
      
      <th scope="col">Players</th>
      <th scope="col">Score</th>
      <th scope="col">Actions</th>
      
    </tr>
  </thead>
  
  <tbody>

<c:forEach items="${getAllPlayers}" var="player">
<tr>
 <td><c:out value="${player.playerName}"></c:out></td>
 <td><c:out value="${player.score}"></c:out></td>
 <td> <form > <!-- add path -->
		 <input type="hidden" name="_method" value="delete">
		<input type="submit" value="Delete" class="btn-danger">
		</form>
		</td>
 	
</tr>

</c:forEach>
</tbody>
</table> 
</div>
<br/>
<div id="fun">
	<button>Let's Play</button>
	<button>Create question</button>
	</div>
	


</body>
</html>