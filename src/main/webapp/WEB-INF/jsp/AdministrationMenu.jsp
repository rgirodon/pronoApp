<li class="dropdown">
	<a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message code="menu.administration"/> <b class="caret"></b></a>
    <ul class="dropdown-menu">
      <c:if test="${ user.admin }">
      	<li><a href="CreatePublicLeague.do"><spring:message code="menu.administration.createPublicLeague"/></a></li>
      </c:if>
      
      <li><a href="CreatePrivateLeague.do"><spring:message code="menu.administration.createPrivateLeague"/></a></li>
      
      <c:if test="${ (not empty league) 
      				 and ((user.isLeagueAdmin(league.id))
      				 	  or (user.admin)) }">
	      <li><a href="EditLeague.do"><spring:message code="menu.administration.league"/></a></li>	      
	      <li><a href="ListUsers.do"><spring:message code="menu.administration.participants"/></a></li>
      </c:if>
      
      <c:if test="${ (not empty league) 
                     and (empty league.inheritsGamesFromLeague)
      				 and ((user.isLeagueAdmin(league.id))
      				 	  or (user.admin)) }">
	      <li><a href="ListGames.do"><spring:message code="menu.administration.adminGames"/></a></li>
      </c:if>
        
    </ul>
</li>