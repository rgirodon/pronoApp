<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
  <head>
    <title><spring:message code="hello.title"/>Welcome to PronoClub</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/theme.bootstrap.css" rel="stylesheet">
    <link href="addons/pager/jquery.tablesorter.pager.css" rel="stylesheet">
    <link href="css/hello.css" rel="stylesheet">
    <link href="css/common.css" rel="stylesheet">
    <script src="js/jquery-1.10.2.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.tablesorter.js"></script>
	<script src="js/jquery.tablesorter.widgets.js"></script>	
	<script src="addons/pager/jquery.tablesorter.pager.js"></script>
	<script id="js">
	
	$(function() {

		$('#bet-button').click(function() {
		
			var btn = $(this);
		    btn.button('loading');
		});
		
		$("#fullComputedBetGamesTable").tablesorter({

			theme : "bootstrap",
	
			widthFixed: true,
	
			headerTemplate : '{content} {icon}', // new in v2.7. Needed to add the bootstrap icon!
	
			widgets : [ "uitheme", "zebra" ]		
		})
		.tablesorterPager({
	
			container: $(".fullComputedBetGamesTable-ts-pager"),
	
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
    	<%@include file="Menu.jsp" %>
	</div>
  	<div class="jumbotron">
		<div class="container">	    
		  <h2><spring:message code="hello.header.welcome"/> <c:out value="${ league.name }" /> <c:out value="${ user.displayName }" /></h2>
		  <p><spring:message code="hello.youHave"/> <c:out value="${ user.getPointsForLeague(league.id) }" /> point(s)</p>
		  <p><spring:message code="hello.yourRanking"/> <c:out value="${ ranking }" /> / <c:out value="${ nbTotalUsers }" /></p>
		  <p><spring:message code="hello.youLeave"/> <a class="btn btn-lg btn-default" href="SignOut.do" role="button"><span class="glyphicon glyphicon-off"></span> <spring:message code="hello.signout"/></a></p>
		</div>		
	</div>
	<div class="container">
      <!-- Example row of columns -->
      <div class="row">
      
        <div class="col-md-4">
          
          <h2><spring:message code="hello.betForGames"/></h2>
          
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
	          		<div class="col-xs-4 col-md-4">
			  			<small><c:out value="${ gameForBet.team1 }" /></small>
			  		</div>
			  		<div class="col-xs-2 col-md-2">
			  			<input type="text" class="form-control score-input" name="<c:out value="${ gameForBet.idGame }" />_scoreTeam1" value="<c:out value="${ gameForBet.scoreTeam1 }" />">
			  		</div>
			  		<div class="col-xs-2 col-md-2">
			  			<input type="text" class="form-control score-input" name="<c:out value="${ gameForBet.idGame }" />_scoreTeam2" value="<c:out value="${ gameForBet.scoreTeam2 }" />"> 
		  			</div>
		  			<div class="col-xs-4 col-md-4">
						<small><c:out value="${ gameForBet.team2 }" /></small>
					</div>
			  	</div>
			  </c:forEach>
			  
	          <div class="row">
	          	<div class="col-md-12 bet-button">
	          		<button id="bet-button" class="btn btn-default" data-loading-text="<spring:message code="wait"/>"><span class="glyphicon glyphicon-ok"></span> <spring:message code="hello.bet"/></button>
	          	</div>
	          </div>
	          
          </form>

        </div>
        
	        <div class="col-md-4">
	        	<h2><spring:message code="hello.ongoingBets"/></h2>
	        	<br/>
	        	<c:forEach var="notComputedBetGame" items="${notComputedBetGames}" >
		          	<div class="row bet-row">
			          	<div class="col-xs-4 col-md-4">
					  		<small><c:out value="${ notComputedBetGame.team1 }" /></small>
					  	</div>
					  	<div class="col-xs-2 col-md-2">
				  			<input disabled="true" type="text" class="form-control score-input" name="<c:out value="${ notComputedBetGame.idGame }" />_scoreTeam1" value="<c:out value="${ notComputedBetGame.scoreTeam1 }" />">
				  		</div>
				  		<div class="col-xs-2 col-md-2"> 
				  			<input disabled="true" type="text" class="form-control score-input" name="<c:out value="${ notComputedBetGame.idGame }" />_scoreTeam2" value="<c:out value="${ notComputedBetGame.scoreTeam2 }" />"> 
				  		</div>
				  		<div class="col-xs-4 col-md-4">
		  					<small><c:out value="${ notComputedBetGame.team2 }" /></small>
		  					<a href="SeeOtherPronostics.do?idGame=<c:out value="${ notComputedBetGame.idGame }" />" data-toggle="modal" data-target="#seeOtherPronosticsModal"><span class="glyphicon glyphicon-eye-open"></span></a> 
				  		</div>
				  	</div>
				 </c:forEach>
				 <br/>
				 <h4><spring:message code="hello.ongoingBets.slogan"/></h4>
	        </div>	    
        
	        <div class="col-md-4">
	          <h2><spring:message code="hello.ranking"/></h2>
	          <p>
	          	  <table class="table table-striped table-condensed">
		          	<thead>
		          		<tr>
		          			<th><spring:message code="hello.ranking.rank"/></th>
		          			<th><spring:message code="hello.ranking.user"/></th>
		          			<th><spring:message code="hello.ranking.points"/></th>
		          		</tr>
		          	</thead>
		          	<c:forEach var="rankingUser" items="${rankingUsers}" begin="0" end="2" varStatus="rank">
		          		<tr>
		          			<td><c:out value="${ rank.index + 1 }" /></td>
		          			<td><c:out value="${ rankingUser.displayName }" /></td>
		          			<td><c:out value="${ rankingUser.getPointsForLeague(league.id) }" /></td>
		          		</tr>
		          	</c:forEach>
		          </table>
	          </p>
	          <p><button class="btn btn-default" data-toggle="modal" data-target="#rankingModal"><span class="glyphicon glyphicon-list"></span> <spring:message code="hello.ranking.viewFull"/></button></p>
	        </div>
	        
	     </div>
	     
	     <div class="row">
        
	        <div class="col-md-6">
	          <h2><spring:message code="hello.history"/></h2>
	          <p>
		          <table class="table table-striped table-condensed">
		          	<thead>
		          		<tr>
		          			<th><spring:message code="hello.history.game"/></th>
		          			<th><spring:message code="hello.history.score"/></th>
		          			<th><spring:message code="hello.history.pronostic"/></th>
		          			<th><spring:message code="hello.history.points"/></th>
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
	          <p><button class="btn btn-default" data-toggle="modal" data-target="#historyModal"><span class="glyphicon glyphicon-time"></span> <spring:message code="hello.history.viewFull"/></button></p>
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
		        <h4 class="modal-title" id="historyModalLabel"><spring:message code="hello.history"/></h4>
		      </div>
		      <div class="modal-body">
		        <table id="fullComputedBetGamesTable" class="table table-striped table-condensed">
		          	<thead>
		          		<tr>
		          			<th><spring:message code="hello.history.game"/></th>
		          			<th><spring:message code="hello.history.score"/></th>
		          			<th><spring:message code="hello.history.pronostic"/></th>
		          			<th><spring:message code="hello.history.points"/></th>
		          		</tr>
		          	</thead>
		          	<tfoot>
						<tr>
		          			<th><spring:message code="hello.history.game"/></th>
		          			<th><spring:message code="hello.history.score"/></th>
		          			<th><spring:message code="hello.history.pronostic"/></th>
		          			<th><spring:message code="hello.history.points"/></th>
						</tr>
						<tr>
							<th colspan="4" class="fullComputedBetGamesTable-ts-pager form-horizontal">
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
		        <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="hello.close"/></button>
		      </div>
		    </div>
		  </div>
		</div>
		
		<div class="modal fade" id="rankingModal" tabindex="-1" role="dialog" aria-labelledby="rankingModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		        <h4 class="modal-title" id="historyModalLabel"><spring:message code="hello.ranking"/></h4>
		      </div>
		      <div class="modal-body">
		        <table class="table table-striped table-condensed">
	          	<thead>
	          		<tr>
	          			<th><spring:message code="hello.ranking.rank"/></th>
	          			<th><spring:message code="hello.ranking.user"/></th>
	          			<th><spring:message code="hello.ranking.points"/></th>
	          			<th><spring:message code="hello.ranking.pronos"/></th>
	          			<th><spring:message code="hello.ranking.correct.withExact"/></th>
	          			<th><spring:message code="hello.ranking.exact"/></th>
	          			<th><spring:message code="hello.ranking.correct.withoutExact"/></th>
	          		</tr>
	          	</thead>
	          	<c:forEach var="rankingUser" items="${rankingUsers}" varStatus="rank">
	          		<tr>
	          			<td><c:out value="${ rank.index + 1 }" /></td>
	          			<td><c:out value="${ rankingUser.displayName }" /></td>
	          			<td><c:out value="${ rankingUser.getPointsForLeague(league.id) }" /></td>
	          			<td><c:out value="${ rankingUser.getNbComputedPronosForLeague(league.id) }" /></td>
	          			<td><c:out value="${ rankingUser.getNbCorrectResultsForLeague(league.id) }" /></td>
	          			<td><c:out value="${ rankingUser.getNbExactScoresForLeague(league.id) }" /></td>
	          			<td><c:out value="${ rankingUser.getNbCorrectButInexactResultsForLeague(league.id) }" /></td>
	          		</tr>
	          	</c:forEach>
	          </table>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="hello.close"/></button>
		      </div>
		    </div>
		  </div>
		</div>	
    
  </body>
</html>