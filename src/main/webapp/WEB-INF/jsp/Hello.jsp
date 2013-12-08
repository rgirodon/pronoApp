<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>Welcome to Pronostico</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/theme.bootstrap.css" rel="stylesheet">
    <link href="addons/pager/jquery.tablesorter.pager.css" rel="stylesheet">
    <link href="css/hello.css" rel="stylesheet">
    <script src="js/jquery-1.10.2.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.tablesorter.js"></script>
	<script src="js/jquery.tablesorter.widgets.js"></script>	
	<script src="addons/pager/jquery.tablesorter.pager.js"></script>
	<script id="js">
	
	$(function() {

		$("#fullComputedBetGamesTable").tablesorter({

			theme : "bootstrap",
	
			widthFixed: true,
	
			headerTemplate : '{content} {icon}', // new in v2.7. Needed to add the bootstrap icon!
	
			widgets : [ "uitheme", "zebra" ]		
		})
		.tablesorterPager({
	
			container: $(".ts-pager"),
	
			cssGoto  : ".pagenum",
	
			removeRows: false,
	
			output: '{startRow} - {endRow} / {filteredRows} ({totalRows})'	
		});
		
		$('#seeOtherPronosticsModal').on('hidden.bs.modal', function () {
		    $('#seeOtherPronosticsModal').removeData('bs.modal');
		});
	});

    </script>
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
          
          <br/>
          
          <c:if test="${ not empty betConfirmMessage }">
          	<div class="alert alert-success"><c:out value="${ betConfirmMessage }" /></div>
          </c:if>
          
          <c:if test="${ not empty betErrorMessage }">
          	<div class="alert alert-danger"><c:out value="${ betErrorMessage }" /></div>
          </c:if>
          
          <form class="form-inline" method="post" role="form" action="Bet.do">
          
	          <c:forEach var="gameForBet" items="${gamesForBet}" >
	          	<div class="row bet-row">
		          	<div class="col-md-4 bet-team-home">
				  		<small><c:out value="${ gameForBet.team1 }" /></small>
				  	</div>
				  	<div class="col-md-2">
			  			<input type="text" class="form-control score-input" name="<c:out value="${ gameForBet.idGame }" />_scoreTeam1" value="<c:out value="${ gameForBet.scoreTeam1 }" />">
			  		</div>
			  		<div class="col-md-2"> 
			  			<input type="text" class="form-control score-input" name="<c:out value="${ gameForBet.idGame }" />_scoreTeam2" value="<c:out value="${ gameForBet.scoreTeam2 }" />"> 
			  		</div>
			  		<div class="col-md-4 bet-team-away">
			  			<small><c:out value="${ gameForBet.team2 }" /></small>
			  		</div>
			  	</div>
			  </c:forEach>
			  
	          <div class="row">
	          	<div class="col-md-12 bet-button">
	          		<button class="btn btn-default"><span class="glyphicon glyphicon-ok"></span> Bet</button>
	          	</div>
	          </div>
	          
          </form>

        </div>
        
	        <div class="col-md-4">
	        	<h2>Ongoing bets</h2>
	        	<br/>
	        	<c:forEach var="notComputedBetGame" items="${notComputedBetGames}" >
		          	<div class="row bet-row">
			          	<div class="col-md-4 bet-team-home">
					  		<small><c:out value="${ notComputedBetGame.team1 }" /></small>
					  	</div>
					  	<div class="col-md-2">
				  			<input disabled="true" type="text" class="form-control score-input" name="<c:out value="${ notComputedBetGame.idGame }" />_scoreTeam1" value="<c:out value="${ notComputedBetGame.scoreTeam1 }" />">
				  		</div>
				  		<div class="col-md-2"> 
				  			<input disabled="true" type="text" class="form-control score-input" name="<c:out value="${ notComputedBetGame.idGame }" />_scoreTeam2" value="<c:out value="${ notComputedBetGame.scoreTeam2 }" />"> 
				  		</div>
				  		<div class="col-md-4 bet-team-away">
		  					<small><c:out value="${ notComputedBetGame.team2 }" /></small>
		  					<a href="SeeOtherPronostics.do?idGame=<c:out value="${ notComputedBetGame.idGame }" />" data-toggle="modal" data-target="#seeOtherPronosticsModal"><span class="glyphicon glyphicon-eye-open"></span></a> 
				  		</div>
				  	</div>
				 </c:forEach>
				 <br/>
				 <h4>Hope for the best, prepare for the worst</h4>
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
        
        <div class="row">
        
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
		          	<c:forEach var="computedBetGame" items="${computedBetGames}" begin="0" end="2">
		          		<tr>
		          			<td><c:out value="${ computedBetGame.game.label }" /></td>
		          			<td><c:out value="${ computedBetGame.game.score }" /></td>
		          			<td><c:out value="${ computedBetGame.pronostic.score }" /></td>
		          			<td><c:out value="${ computedBetGame.pronostic.points }" /></td>
		          		</tr>
		          	</c:forEach>
		          </table>
	          </p>
	          <p><button class="btn btn-default" data-toggle="modal" data-target="#historyModal"><span class="glyphicon glyphicon-time"></span> View full history &raquo;</button></p>
	        </div>
                
      	</div>
      
        <!-- SeeOtherPronostics Modal -->
		<div class="modal fade" id="seeOtherPronosticsModal" tabindex="-1" role="dialog" aria-labelledby="seeOtherPronosticsModallLabel" aria-hidden="true">		  
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
		        <table id="fullComputedBetGamesTable" class="table table-striped table-condensed">
		          	<thead>
		          		<tr>
		          			<th>Game</th>
		          			<th>Score</th>
		          			<th>Pronostic</th>
		          			<th>Points</th>
		          		</tr>
		          	</thead>
		          	<tfoot>
						<tr>
		          			<th>Game</th>
		          			<th>Score</th>
		          			<th>Pronostic</th>
		          			<th>Points</th>
						</tr>
						<tr>
							<th colspan="4" class="ts-pager form-horizontal">
								<button type="button" class="btn first"><i class="icon-step-backward glyphicon glyphicon-step-backward"></i></button>
								<button type="button" class="btn prev"><i class="icon-arrow-left glyphicon glyphicon-backward"></i></button>
								<span class="pagedisplay"></span> <!-- this can be any element, including an input -->
								<button type="button" class="btn next"><i class="icon-arrow-right glyphicon glyphicon-forward"></i></button>
								<button type="button" class="btn last"><i class="icon-step-forward glyphicon glyphicon-step-forward"></i></button>
								<select class="pagesize input-mini" title="Select page size">
									<option selected="selected" value="10">10</option>
									<option value="20">20</option>
									<option value="30">30</option>
									<option value="40">40</option>
								</select>
								<select class="pagenum input-mini" title="Select page number"></select>
							</th>
						</tr>
					</tfoot>
		          	<c:forEach var="computedBetGame" items="${computedBetGames}">
		          		<tr>
		          			<td><c:out value="${ computedBetGame.game.label }" /></td>
		          			<td><c:out value="${ computedBetGame.game.score }" /></td>
		          			<td><c:out value="${ computedBetGame.pronostic.score }" /></td>
		          			<td><c:out value="${ computedBetGame.pronostic.points }" /></td>
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
		    </div>
		  </div>
		</div>
	
    
  </body>
</html>