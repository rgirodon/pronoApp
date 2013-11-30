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

		  <c:if test="${ not empty actionMessage }">
		  	<div class="alert alert-success"><c:out value="${ actionMessage }" /></div>
		  </c:if>
		  <c:if test="${ not empty actionError }">
		  	<div class="alert alert-danger"><c:out value="${ actionError }" /></div>
		  </c:if>

		  <p>
		  	<a href="New.do" role="button" class="btn btn-default"><span class="glyphicon glyphicon-plus"></span> New Game</a>
		  </p>
          <p>
	          <table id="gamesTable" class="table table-striped table-condensed">
	          	<thead>
	          		<tr>
	          			<th data-sorter="shortDate" data-date-format="yyyymmdd">Date</th>
	          			<th>Game</th>
	          			<th>Score</th>
	          			<th>Closed</th>
	          			<th>Points Computed</th>
	          			<th>Action</th>
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
		          			<td>
		          				<c:if test="${ game.editable }">
		          					<a href="Edit.do?id=<c:out value="${ game.id }" />" role="button" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-edit"></span> Edit</a>
		          				</c:if>
		          				<c:if test="${ game.closable }">
		          					<a href="Close.do?id=<c:out value="${ game.id }" />" role="button" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-eye-close"></span> Close</a>
		          				</c:if>
		          				<c:if test="${ game.openable }">
		          					<a href="Open.do?id=<c:out value="${ game.id }" />" role="button" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-eye-open"></span> Open</a>
		          				</c:if>
		          				<c:if test="${ game.pointsComputable }">
		          					<a href="ComputePoints.do?id=<c:out value="${ game.id }" />" role="button" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-dashboard"></span> Compute points</a>
		          				</c:if>
		          				<c:if test="${ game.deletable }">
		          					<a href="Delete.do?id=<c:out value="${ game.id }" />" role="button" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-remove"></span> Delete</a>
		          				</c:if>
		          			</td>
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