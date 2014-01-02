<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>Sign In</title>
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
		<img src="img/PronoClub.png" height="200" />
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
        
        <h2 class="form-signin-heading">Already registered, please sign in</h2> 
        
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
	<!-- Modal -->
	<div class="modal fade" id="pronoClubRulesModal" tabindex="-1" role="dialog" aria-labelledby="pronoClubRulesLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title" id="pronoClubRulesLabel">Please, read this</h4>
	      </div>
	      <div class="modal-body">
	        <div class="container">
	        	<h2 class="form-signin-heading">The Rules of PronoClub</h2>
	        	<br/>
		      	<h4 class="form-signin-heading">1st Rule : You do talk about PronoClub</h4>
		        <h4 class="form-signin-heading">2nd Rule : You DO talk about PronoClub</h4>
		        <h4 class="form-signin-heading">3rd Rule : If the league admin closes the game, the bets for this game are over</h4>
		        <h4 class="form-signin-heading">4th Rule : As many open games as the league admin wants</h4>
		        <h4 class="form-signin-heading">5th Rule : No limitation on players in a league</h4>
		        <h4 class="form-signin-heading">6th Rule : No short sleeves shirt</h4>
		        <h4 class="form-signin-heading">7th Rule : Leagues will go as long as they have to</h4>
		        <h4 class="form-signin-heading">8th Rule : If this is your first time at PronoClub, you HAVE to bet</h4>
		    </div>
	      </div>
	    </div>
	  </div>
	</div>

    
  </body>
</html>