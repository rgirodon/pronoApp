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

      <form class="form-signin" method="post" action="SignIn.do">
        <h2 class="form-signin-heading">Already registered ?</h2> 
        <h2 class="form-signin-heading">Please sign in</h2>
        
        <c:if test="${ not empty badLogin }">
			<div class="alert alert-danger badLogin">Bad login, retry</div>
		</c:if>
		
		<c:if test="${ not empty param.registerInformation }">
			<div class="alert alert-success registerInformation"><c:out value="${ param.registerInformation }" /></div>
		</c:if>
        
        <input name="login" type="text" class="form-control" placeholder="Email address" required autofocus>
        <input name="password" type="password" class="form-control" placeholder="Password" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        
        <br/>
	    <h2 class="form-signin-heading">New user ?</h2>
	    <a href="Register.do" role="button" class="btn btn-lg btn-primary btn-block">Register</a>
      </form>

    </div>

    <script src="js/jquery-1.10.2.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>