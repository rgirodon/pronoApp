<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>Hello World</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/hello.css" rel="stylesheet">
  </head>
  <body>
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
          	<p><span class="text-info"><c:out value="${ betConfirmMessage }" /></span></p>
          </c:if>
          
          <c:if test="${ not empty betErrorMessage }">
          	<p><span class="text-danger"><c:out value="${ betErrorMessage }" /></span></p>
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
          <p>TODO : put last lines of history here</p>
          <p><a class="btn btn-default" href="#" role="button"><span class="glyphicon glyphicon-time"></span> View full history &raquo;</a></p>
        </div>
        <div class="col-md-4">
          <h2>Ranking</h2>
          <p>TODO : put first lines of ranking here</p>
          <p><a class="btn btn-default" href="#" role="button"><span class="glyphicon glyphicon-list"></span> View full ranking &raquo;</a></p>
        </div>        
      </div>
    <script src="js/jquery-1.10.2.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>