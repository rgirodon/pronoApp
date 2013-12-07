<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>Change my password</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="css/bootstrap.min.css" rel="stylesheet"> 
    <link href="css/hello.css" rel="stylesheet">
    <script src="js/jquery-1.10.2.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
  </head>
  <body>
    <div>
	  	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<div class="navbar-header">
	    		<a class="navbar-brand" href="Welcome.do">Pronostico</a>
	  		</div>
	  		<div class="collapse navbar-collapse">
	  			<ul class="nav navbar-nav">
	  				
	  				<li class="dropdown">
				    	<a href="#" class="dropdown-toggle" data-toggle="dropdown">Settings <b class="caret"></b></a>
				        <ul class="dropdown-menu">
				          <li><a href="ChangeMyPassword.do">Change my password</a></li>
				          <c:if test="${ user.admin }">
				          	<li><a href="Games/List.do">Admin Games</a></li>
	<!-- 			          <li><a href="#">Users</a></li> -->
	<!-- 			          <li><a href="#">Pronostics</a></li> -->
						  </c:if>
				        </ul>
				    </li>
				    
	  			</ul>
	  		</div>
		</nav>
	</div>
	<div class="container">
      <!-- Example row of columns -->
      <div class="row">
        <div class="col-md-12">
          
          <br/>
          <br/>
          
          <h2>Change my password</h2>

		  <c:if test="${ not empty changeMyPasswordInformation }">
		  	<div class="alert alert-success registerInformation"><c:out value="${ changeMyPasswordInformation }" /></div>
		  </c:if>

		  <c:if test="${ not empty errors }">
		  	<div class="alert alert-danger">
		  		<c:forEach items="${ errors }" var="error">
		  			<c:out value="${ error }" /><br/>
		  		</c:forEach>
		  	</div>
		  </c:if>

		  <form method="post" role="form" action="ChangeMyPassword.do">
			  <div class="form-group">
			    <label for="login">Email address</label>
			    <input disabled="true" type="text" class="form-control" id="login" value="<c:out value="${ user.login }" />">
			  </div>
			  <div class="form-group">
			    <label for="exampleInputPassword1">Old Password</label>
			    <input required="true" type="password" class="form-control" name="oldPassword" />
			  </div>
			  <div class="form-group">
			    <label for="exampleInputFile">New Password</label>
			    <input required="true" type="password" class="form-control" name="newPassword" />
			  </div>
			  <div class="form-group">
			    <label for="exampleInputFile">Re-Enter New Password</label>
			    <input required="true" type="password" class="form-control" name="reEnterNewPassword" /> 
			  </div>
			  
          	  <button class="btn btn-default"><span class="glyphicon glyphicon-ok"></span> Change</button>
		  </form>

          
		</div>
	  </div>
	</div>
  </body>
</html>