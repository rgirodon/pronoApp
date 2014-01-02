<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>Games Administration</title>
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
	  	<%@include file="Menu.jsp" %>
	</div>
	<div class="container">
      <!-- Example row of columns -->
      <div class="row">
        <div class="col-md-12">
          
          <br/>
          <br/>
          
          <h2>List of Games</h2>

		  <c:if test="${ not empty actionMessage }">
		  	<div class="alert alert-success"><c:out value="${ actionMessage }" /></div>
		  </c:if>
		  <c:if test="${ not empty actionError }">
		  	<div class="alert alert-danger"><c:out value="${ actionError }" /></div>
		  </c:if>

		  <p>
		  	<a href="NewGame.do" role="button" class="btn btn-default"><span class="glyphicon glyphicon-plus"></span> New Game</a>
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
	          	<tfoot>
					<tr>
						<th data-sorter="shortDate" data-date-format="yyyymmdd">Date</th>
	          			<th>Game</th>
	          			<th>Score</th>
	          			<th>Closed</th>
	          			<th>Points Computed</th>
	          			<th>Action</th>
					</tr>
					<tr>
						<th colspan="6" class="ts-pager form-horizontal">
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
	          	<tbody>
		          	<c:forEach var="game" items="${games}">		          		
		          		<c:choose>
							<c:when test="${ game.pointsComputed }"> 
								<tr>
							</c:when>
							<c:when test="${ game.closed }">
								<tr class="danger">
							</c:when>
							<c:when test="${ not game.closed }">
								<tr class="success">
							</c:when>	
						</c:choose>
		          			<td><c:out value="${ game.formattedDate }" /></td>
		          			<td><c:out value="${ game.label }" /></td>
		          			<td><c:out value="${ game.score }" /></td>
		          			<td><c:out value="${ game.closed }" /></td>
		          			<td><c:out value="${ game.pointsComputed }" /></td>
		          			<td>
		          				<c:if test="${ game.editable }">
		          					<a href="EditGame.do?gameId=<c:out value="${ game.id }" />" role="button" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-edit"></span> Edit</a>
		          				</c:if>
		          				<c:if test="${ game.closable }">
		          					<a href="CloseGame.do?gameId=<c:out value="${ game.id }" />" role="button" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-eye-close"></span> Close</a>
		          				</c:if>
		          				<c:if test="${ game.openable }">
		          					<a href="OpenGame.do?gameId=<c:out value="${ game.id }" />" role="button" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-eye-open"></span> Open</a>
		          				</c:if>
		          				<c:if test="${ game.pointsComputable }">
		          					<a href="ComputePointsForGame.do?gameId=<c:out value="${ game.id }" />" role="button" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-dashboard"></span> Compute points</a>
		          				</c:if>
		          				<c:if test="${ game.deletable }">
		          					<a href="DeleteGame.do?gameId=<c:out value="${ game.id }" />" role="button" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-remove"></span> Delete</a>
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