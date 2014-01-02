<nav class="navbar-black navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#menu-collapse">
	      <span class="sr-only">Toggle navigation</span>
	      <span class="icon-bar"></span>
	      <span class="icon-bar"></span>
	      <span class="icon-bar"></span>
	    </button>	    
   		<a href="Welcome.do"><img src="img/PronoClub.png" height="50" /></a>
 	</div>
 	<div class="collapse navbar-collapse" id="menu-collapse">
 			<ul class="nav navbar-nav">
 				
 				<%@include file="MyAccountMenu.jsp" %>
		    
		    	<%@include file="AdministrationMenu.jsp" %>
		    
 			</ul>
 			
 			<%@include file="SelectLeagueMenu.jsp" %> 			
 	</div>
</nav>