<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>Welcome to PronoApp</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/hello.css" rel="stylesheet">
  </head>
  <body>
    <div>
	  	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<div class="navbar-header">
	    		<a class="navbar-brand" href="Welcome.do">PronoApp</a>
	  		</div>
	  		<div class="collapse navbar-collapse">
	  			<ul class="nav navbar-nav">
	  				<c:if test="${ user.admin }">
		  				<li class="dropdown">
					    	<a href="#" class="dropdown-toggle" data-toggle="dropdown">Admin <b class="caret"></b></a>
					        <ul class="dropdown-menu">
					          <li><a href="Games/List.do">Games</a></li>
		<!-- 			          <li><a href="#">Users</a></li> -->
		<!-- 			          <li><a href="#">Pronostics</a></li> -->
					        </ul>
					    </li>
				    </c:if>
	  			</ul>
	  		</div>
		</nav>
	</div>
  	<div class="jumbotron">
		<div class="container">	    
		  <h2>Welcome <c:out value="${ user.login }" /></h2>
		  <p>We are very pleased to see you here.</p>
		  <p>You have <c:out value="${ user.points }" /> point(s)</p>
		  <p>Your ranking : <c:out value="${ ranking }" /> / <c:out value="${ nbTotalUsers }" /></p>
		  <p>If you want to leave <a class="btn btn-lg btn-default" href="SignOut.do" role="button"><span class="glyphicon glyphicon-off"></span> Sign Out</a></p>
		</div>		
	</div>
	<div class="container">
      <!-- Example row of columns -->
      <div class="row">
        <div class="col-md-4">
          
          <h2>Bet for games</h2>
          
          <c:if test="${ not empty betConfirmMessage }">
          	<div class="alert alert-success"><c:out value="${ betConfirmMessage }" /></div>
          </c:if>
          
          <c:if test="${ not empty betErrorMessage }">
          	<div class="alert alert-danger"><c:out value="${ betErrorMessage }" /></div>
          </c:if>
          
          <form class="form-inline" method="post" role="form" action="Bet.do">
          
	          <c:forEach var="gameForBet" items="${gamesForBet}" >
			  	<p><c:out value="${ gameForBet.team1 }" /> 
			  		<input type="text" class="form-control score-input" name="<c:out value="${ gameForBet.idGame }" />_scoreTeam1" value="<c:out value="${ gameForBet.scoreTeam1 }" />">
			  		 - 
			  		<input type="text" class="form-control score-input" name="<c:out value="${ gameForBet.idGame }" />_scoreTeam2" value="<c:out value="${ gameForBet.scoreTeam2 }" />"> 
			  	   <c:out value="${ gameForBet.team2 }" />
			  	</p>
			  </c:forEach>
			  
	          <p><button class="btn btn-default"><span class="glyphicon glyphicon-ok"></span> Bet</button></p>
	          
          </form>
        </div>
        <div class="col-md-4">
          <h2>History</h2>
          <p>
	          <table class="table table-striped table-condensed">
	          	<thead>
	          		<tr>
	          			<th>Game</th>
	          			<th>Score</th>
	          			<th>Pronostic</th>
	          			<th>Points</th>
	          		</tr>
	          	</thead>
	          	<c:forEach var="betGame" items="${betGames}" begin="0" end="2">
	          		<tr>
	          			<td><c:out value="${ betGame.game.label }" /></td>
	          			<td><c:out value="${ betGame.game.score }" /></td>
	          			<td><c:out value="${ betGame.pronostic.score }" /></td>
	          			<td><c:out value="${ betGame.pronostic.points }" /></td>
	          		</tr>
	          	</c:forEach>
	          </table>
          </p>
          <p><button class="btn btn-default" data-toggle="modal" data-target="#historyModal"><span class="glyphicon glyphicon-time"></span> View full history &raquo;</button></p>
        </div>
        <div class="col-md-4">
          <h2>Ranking</h2>
          <p>
          	  <table class="table table-striped table-condensed">
	          	<thead>
	          		<tr>
	          			<th>User</th>
	          			<th>Points</th>
	          		</tr>
	          	</thead>
	          	<c:forEach var="rankingUser" items="${rankingUsers}" begin="0" end="2">
	          		<tr>
	          			<td><c:out value="${ rankingUser.login }" /></td>
	          			<td><c:out value="${ rankingUser.points }" /></td>
	          		</tr>
	          	</c:forEach>
	          </table>
          </p>
          <p><button class="btn btn-default" data-toggle="modal" data-target="#rankingModal"><span class="glyphicon glyphicon-list"></span> View full ranking &raquo;</button></p>
        </div>        
      </div>
    
	    <!-- History Modal -->
		<div class="modal fade" id="historyModal" tabindex="-1" role="dialog" aria-labelledby="historyModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		        <h4 class="modal-title" id="historyModalLabel">History</h4>
		      </div>
		      <div class="modal-body">
		        <table class="table table-striped table-condensed">
		          	<thead>
		          		<tr>
		          			<th>Game</th>
		          			<th>Score</th>
		          			<th>Pronostic</th>
		          			<th>Points</th>
		          		</tr>
		          	</thead>
		          	<c:forEach var="betGame" items="${betGames}">
		          		<tr>
		          			<td><c:out value="${ betGame.game.label }" /></td>
		          			<td><c:out value="${ betGame.game.score }" /></td>
		          			<td><c:out value="${ betGame.pronostic.score }" /></td>
		          			<td><c:out value="${ betGame.pronostic.points }" /></td>
		          		</tr>
		          	</c:forEach>
		          </table>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		      </div>
		    </div>
		  </div>
		</div>
		
		<div class="modal fade" id="rankingModal" tabindex="-1" role="dialog" aria-labelledby="rankingModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		        <h4 class="modal-title" id="historyModalLabel">Ranking</h4>
		      </div>
		      <div class="modal-body">
		        <table class="table table-striped table-condensed">
	          	<thead>
	          		<tr>
	          			<th>User</th>
	          			<th>Points</th>
	          		</tr>
	          	</thead>
	          	<c:forEach var="rankingUser" items="${rankingUsers}">
	          		<tr>
	          			<td><c:out value="${ rankingUser.login }" /></td>
	          			<td><c:out value="${ rankingUser.points }" /></td>
	          		</tr>
	          	</c:forEach>
	          </table>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		      </div>
		    </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
	
    <script src="js/jquery-1.10.2.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>