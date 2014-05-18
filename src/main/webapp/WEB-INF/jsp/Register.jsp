<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title><spring:message code="register.title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/signin.css" rel="stylesheet">
    <link href="css/common.css" rel="stylesheet">
    <script src="js/jquery-1.10.2.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script id="js">	
	$(function() {

		$('#register-button').click(function() {
		
			var btn = $(this);
		    btn.button('loading');
		});
	});
	</script>
	
  </head>
  <body>
    <div class="navbar-black navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container center">
		<img src="img/PronoClub.jpg" height="200" />
      </div>
    </div>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <div class="container">

      <form class="form-signin" method="post" action="Register.do">
        <h2 class="form-signin-heading"><spring:message code="register.header"/></h2> 
        
        <c:if test="${ not empty errors }">
	  		<div class="alert alert-danger">
	  			<c:forEach items="${ errors }" var="error">
	  				<c:out value="${ error }" /><br/>
	  			</c:forEach>
	  		</div>
	  	</c:if>
        
        <input name="login" type="email" class="form-control" placeholder="<spring:message code="register.email"/>" required autofocus value="<c:out value="${ registerDTO.login }" />">
        <input name="displayName" type="text" class="form-control" placeholder="<spring:message code="register.displayName"/>" required autofocus value="<c:out value="${ registerDTO.displayName }" />">
        <input name="password" type="password" class="form-control" placeholder="<spring:message code="register.password"/>" required>
        <input name="reEnterPassword" type="password" class="form-control" placeholder="<spring:message code="register.reEnterPassword"/>" required>
        <button id="register-button" data-loading-text="<spring:message code="wait"/>" class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="register.submit"/></button>
      </form>

    </div>

    <script src="js/jquery-1.10.2.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>