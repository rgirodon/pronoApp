<li class="dropdown">
	<a href="#" class="dropdown-toggle" data-toggle="dropdown">Administration <b class="caret"></b></a>
    <ul class="dropdown-menu">
      <c:if test="${ user.admin }">
      	<li><a href="CreatePublicLeague.do">Create a public league</a></li>
      </c:if>
      
      <li><a href="CreatePrivateLeague.do">Create a private league</a></li>
      
      <c:if test="${ (not empty league) 
      				 and ((user.isLeagueAdmin(league.id))
      				 	  or (user.admin)) }">
	      <li><a href="EditLeague.do">Admin league</a></li>
	      <li><a href="ListGames.do">Admin games</a></li>
	      <li><a href="ListUsers.do">Admin participants</a></li>
      </c:if>
    </ul>
</li>