<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>Change my display name</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="css/bootstrap.min.css" rel="stylesheet"> 
    <link href="css/hello.css" rel="stylesheet">
    <link href="css/common.css" rel="stylesheet">
    <script src="js/jquery-1.10.2.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
  </head>
  <body>
    <div>
	  	<%@include file="Menu.jsp" %>
	</div>
	<div class="container">
      <!-- Example row of columns -->
      <div class="row">
        <div class="col-md-12">
          
          <br/>
          <br/>
          
          <h2>Change my display name</h2>

		  <c:if test="${ not empty changeMyDisplayNameInformation }">
		  	<div class="alert alert-success registerInformation"><c:out value="${ changeMyDisplayNameInformation }" /></div>
		  </c:if>

		  <c:if test="${ not empty errors }">
		  	<div class="alert alert-danger">
		  		<c:forEach items="${ errors }" var="error">
		  			<c:out value="${ error }" /><br/>
		  		</c:forEach>
		  	</div>
		  </c:if>

		  <form method="post" role="form" action="ChangeMyDisplayName.do">
			  <div class="form-group">
			    <label for="loginInput">Email address</label>
			    <input disabled="true" type="text" class="form-control" id="loginInput" name="loginInput" value="<c:out value="${ user.login }" />">
			  </div>
			  <div class="form-group">
			    <label for="displayNameInput">Display Name</label>
			    <input type="text" class="form-control" id="displayNameInput" name="displayNameInput" value="<c:out value="${ changeMyDisplayNameDTO.displayNameInput }" />">
			  </div>
          	  <button class="btn btn-default"><span class="glyphicon glyphicon-ok"></span> Change</button>
		  </form>

          
		</div>
	  </div>
	</div>
  </body>
</html>