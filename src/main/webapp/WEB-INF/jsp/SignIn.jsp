<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title><spring:message code="signin.title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/signin.css" rel="stylesheet">
    <link href="css/common.css" rel="stylesheet">
    <script src="js/jquery-1.10.2.min.js"></script>
    <script src="js/bootstrap.min.js"></script>	
    <script type="text/javascript">
    $(function() {
		$('#pronoClubRulesModal').modal('show');
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

      <form class="form-signin" method="post" action="SignIn.do">
        
        <h2 class="form-signin-heading"><spring:message code="signin.alreadyregistered"/></h2> 
        
        <c:if test="${ not empty badLogin }">
			<div class="alert alert-danger badLogin"><spring:message code="signin.badlogin"/></div>
		</c:if>
		
		<c:if test="${ not empty param.registerInformation }">
			<div class="alert alert-success registerInformation"><c:out value="${ param.registerInformation }" /></div>
		</c:if>
        
        <input name="login" type="email" class="form-control" placeholder="<spring:message code="signin.email"/>" required autofocus>
        <input name="password" type="password" class="form-control" placeholder="<spring:message code="signin.password"/>" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="signin.login"/></button>
        
        <br/>
	    <h2 class="form-signin-heading"><spring:message code="signin.newuser"/></h2>
	    <a href="Register.do" role="button" class="btn btn-lg btn-primary btn-block"><spring:message code="signin.register"/></a>	    
	    
      </form>

    </div>
	<!-- Modal -->
	<div class="modal fade" id="pronoClubRulesModal" tabindex="-1" role="dialog" aria-labelledby="pronoClubRulesLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title" id="pronoClubRulesLabel"><spring:message code="signin.rules.readthis"/></h4>
	      </div>
	      <div class="modal-body">
	        <div class="container">
	        	<h2 class="form-signin-heading"><spring:message code="signin.rules.title"/></h2>
	        	<br/>
		      	<h4 class="form-signin-heading"><spring:message code="signin.rules.rule1"/></h4>
		        <h4 class="form-signin-heading"><spring:message code="signin.rules.rule2"/></h4>
		        <h4 class="form-signin-heading"><spring:message code="signin.rules.rule3"/></h4>
		        <h4 class="form-signin-heading"><spring:message code="signin.rules.rule4"/></h4>
		        <h4 class="form-signin-heading"><spring:message code="signin.rules.rule5"/></h4>
		        <h4 class="form-signin-heading"><spring:message code="signin.rules.rule6"/></h4>
		        <h4 class="form-signin-heading"><spring:message code="signin.rules.rule7"/></h4>
		        <h4 class="form-signin-heading"><spring:message code="signin.rules.rule8"/></h4>
		    </div>
	      </div>
	    </div>
	  </div>
	</div>

    
  </body>
</html>