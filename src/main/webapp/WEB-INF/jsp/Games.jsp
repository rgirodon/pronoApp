<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>Games Administration</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="../css/bootstrap.min.css" rel="stylesheet"> 
    <link href="../css/theme.bootstrap.css" rel="stylesheet">
    <link href="../addons/pager/jquery.tablesorter.pager.css" rel="stylesheet">
    <script src="../js/jquery-1.10.2.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/jquery.tablesorter.js"></script>
	<script src="../js/jquery.tablesorter.widgets.js"></script>	
	<script src="../addons/pager/jquery.tablesorter.pager.js"></script>
	<script id="js">
	
	$(function() {

		$("#gamesTable").tablesorter({

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
	});

    </script>
  </head>
  <body>
    <div>
	  	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<div class="navbar-header">
	    		<a class="navbar-brand" href="../Welcome.do">PronoApp</a>
	  		</div>
	  		<div class="collapse navbar-collapse">
	  			<ul class="nav navbar-nav">
	  				<c:if test="${ user.admin }">
		  				<li class="dropdown">
					    	<a href="#" class="dropdown-toggle" data-toggle="dropdown">Admin <b class="caret"></b></a>
					        <ul class="dropdown-menu">
					          <li><a href="List.do">Games</a></li>
		<!-- 			          <li><a href="#">Users</a></li> -->
		<!-- 			          <li><a href="#">Pronostics</a></li> -->
					        </ul>
					    </li>
				    </c:if>
	  			</ul>
	  		</div>
		</nav>
	</div>
	<div class="container">
      <!-- Example row of columns -->
      <div class="row">
        <div class="col-md-12">
          
          <h2>Games</h2>

          <p>
	          <table id="gamesTable" class="table table-striped table-condensed">
	          	<thead>
	          		<tr>
	          			<th data-sorter="shortDate" data-date-format="yyyymmdd">Date</th>
	          			<th>Game</th>
	          			<th>Score</th>
	          			<th>Closed</th>
	          			<th>Points Computed</th>
	          		</tr>
	          	</thead>
	          	<tbody>
		          	<c:forEach var="game" items="${games}">
		          		<tr>
		          			<td><c:out value="${ game.formattedDate }" /></td>
		          			<td><c:out value="${ game.label }" /></td>
		          			<td><c:out value="${ game.score }" /></td>
		          			<td><c:out value="${ game.closed }" /></td>
		          			<td><c:out value="${ game.pointsComputed }" /></td>
		          		</tr>
		          	</c:forEach>
	          	</tbody>
	          </table>
          </p>
		</div>
	  </div>
	</div>
  </body>
</html>