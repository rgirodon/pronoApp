<li class="dropdown">
	<a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message code="menu.myaccount"/> <b class="caret"></b></a>
    <ul class="dropdown-menu">
      <li><a href="ChangeMyPassword.do"><spring:message code="menu.myaccount.myPassword"/></a></li>
      <li><a href="ChangeMyDisplayName.do"><spring:message code="menu.myaccount.myDisplayName"/></a></li>
      <li><a href="ChangeMyDefaultLeague.do"><spring:message code="menu.myaccount.myDefaultLeague"/></a></li>
      <li><a href="MyInvitations.do"><spring:message code="menu.myaccount.myInvitations"/></a></li>
      <li><a href="JoinPublicLeague.do"><spring:message code="menu.myaccount.joinPublicLeague"/></a></li>
      <c:if test="${ not empty league }">
	      <li><a href="InviteFriends.do"><spring:message code="menu.myaccount.inviteFriends"/></a></li>
      </c:if>
    </ul>
</li>