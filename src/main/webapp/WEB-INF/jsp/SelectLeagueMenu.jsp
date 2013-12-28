<script>
$(function() {
	$("#chooseLeagueSelect").val('-1');
	<c:if test="${ not empty league }">
		$("#chooseLeagueSelect").val('<c:out value="${ league.id }" />');
	</c:if>
});
</script>

<form id="chooseLeagueForm" class="navbar-form navbar-left" role="search" action="ChooseLeague.do" method="POST">
    <div class="form-group">
      <select id="chooseLeagueSelect" name="leagueId" class="form-control">
  		<option value="-1">- Select League -</option>
  		<c:forEach var="leagueInvolvedIn" items="${user.leaguesInvolvedIn}">
       		<option value="<c:out value="${ leagueInvolvedIn.id }" />"><c:out value="${ leagueInvolvedIn.name }" /></option>
       	</c:forEach>
	  </select>	  
    </div>
    <button type="submit" class="btn btn-success">Go</button>
</form>

<ul class="nav navbar-nav">
	<a target="_blank" href="Help.do" class="btn btn-info navbar-btn" role="button">Help</a>
</ul>