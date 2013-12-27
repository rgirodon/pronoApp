<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>Join a public league</title>
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

		$("#availablePublicLeaguesTable").tablesorter({

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
	    		<a class="navbar-brand" href="Welcome.do">Pronostico</a>
	  		</div>
	  		<div class="collapse navbar-collapse">
	  			<ul class="nav navbar-nav">

				    <%@include file="MyAccountMenu.jsp" %>
				    
				    <%@include file="AdministrationMenu.jsp" %>
				    
	  			</ul>
	  			
	  			<%@include file="SelectLeagueMenu.jsp" %>
	  			
	  		</div>
		</nav>
	</div>
	<div class="container">
      <!-- Example row of columns -->
      <div class="row">
        <div class="col-md-12">
          
          <br/>
          <br/>
          
          <h2>Available public leagues</h2>

		  <c:if test="${ not empty actionMessage }">
		  	<div class="alert alert-success"><c:out value="${ actionMessage }" /></div>
		  </c:if>
		  <c:if test="${ not empty actionError }">
		  	<div class="alert alert-danger"><c:out value="${ actionError }" /></div>
		  </c:if>

          <p>
	          <table id="availablePublicLeaguesTable" class="table table-striped table-condensed">
	          	<thead>
	          		<tr>
	          			<th>Name</th>
	          			<th>Action</th>
	          		</tr>
	          	</thead>
	          	<tfoot>
					<tr>
	          			<th>Name</th>
	          			<th>Action</th>
					</tr>
					<tr>
						<th colspan="2" class="ts-pager form-horizontal">
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
		          	<c:forEach var="availablePublicLeague" items="${availablePublicLeagues}">		          		
		          		<tr>
		          			<td><c:out value="${ availablePublicLeague.name }" /></td>
		          			<td>
	          					<a href="JoinPublicLeagueAction.do?leagueId=<c:out value="${ availablePublicLeague.id }" />" role="button" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-log-in"></span> Join this league</a>
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