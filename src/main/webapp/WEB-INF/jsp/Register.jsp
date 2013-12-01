<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>Sign In</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/signin.css" rel="stylesheet">
  </head>
  <body>
  
    <div class="container">

      <form class="form-signin" method="post" action="Register.do">
        <h2 class="form-signin-heading">Registration form</h2> 
        
        <c:if test="${ not empty errors }">
	  		<div class="alert alert-danger">
	  			<c:forEach items="${ errors }" var="error">
	  				<c:out value="${ error }" /><br/>
	  			</c:forEach>
	  		</div>
	  	</c:if>
        
        <input name="login" type="text" class="form-control" placeholder="Email address" required autofocus value="<c:out value="${ registerDTO.login }" />">
        <input name="password" type="password" class="form-control" placeholder="Password" required>
        <input name="reEnterPassword" type="password" class="form-control" placeholder="Re-enter Password" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
      </form>

    </div>

    <script src="js/jquery-1.10.2.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>