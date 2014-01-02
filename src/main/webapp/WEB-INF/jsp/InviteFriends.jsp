<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>Invite friends</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="css/bootstrap.min.css" rel="stylesheet"> 
    <link href="css/hello.css" rel="stylesheet">
    <link href="css/common.css" rel="stylesheet">
    <link href="css/datepicker.css" rel="stylesheet">
    <script src="js/jquery-1.10.2.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/bootstrap-datepicker.js"></script>

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
          
          <h2>Invite friends to league <c:out value="${ league.name }" /></h2>
		  
		  <c:if test="${ not empty actionMessage }">
		  	<div class="alert alert-success"><c:out value="${ actionMessage }" /></div>
		  </c:if>
		  
		  <c:if test="${ not empty errors }">
		  	<div class="alert alert-danger">
		  		<c:forEach items="${ errors }" var="error">
		  			<c:out value="${ error }" /><br/>
		  		</c:forEach>
		  	</div>
		  </c:if>


	      <form method="post" role="form" action="InviteFriends.do">
          
			  <div class="form-group">
			    <label for="friend1">Friend #1</label>
			    <input type="text" class="form-control" id="friend1" name="friend1" placeholder="Friend 1" value="">
			  </div>
			  <div class="form-group">
			    <label for="friend2">Friend #2</label>
			    <input type="text" class="form-control" id="friend2" name="friend2" placeholder="Friend 2" value="">
			  </div>
			  <div class="form-group">
			    <label for="friend3">Friend #3</label>
			    <input type="text" class="form-control" id="friend3" name="friend3" placeholder="Friend 3" value="">
			  </div>
			  <div class="form-group">
			    <label for="friend4">Friend #4</label>
			    <input type="text" class="form-control" id="friend4" name="friend4" placeholder="Friend 4" value="">
			  </div>
			  <div class="form-group">
			    <label for="friend5">Friend #5</label>
			    <input type="text" class="form-control" id="friend5" name="friend5" placeholder="Friend 5" value="">
			  </div>
			  	          
	          <button class="btn btn-default"><span class="glyphicon glyphicon-ok"></span> Invite</button>	          
	          
          </form> 
          
		</div>
	  </div>
	</div>
  </body>
</html>