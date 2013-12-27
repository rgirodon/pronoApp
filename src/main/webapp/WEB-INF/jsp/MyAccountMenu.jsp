<li class="dropdown">
	<a href="#" class="dropdown-toggle" data-toggle="dropdown">My Account <b class="caret"></b></a>
    <ul class="dropdown-menu">
      <li><a href="ChangeMyPassword.do">My password</a></li>
      <!-- <li><a href="ChangeMyDefaultLeague.do">My default league</a></li>  -->
      <li><a href="MyInvitations.do">My invitations</a></li>
      <li><a href="JoinPublicLeague.do">Join a public league</a></li>
      <c:if test="${ not empty league }">
	      <li><a href="InviteFriends.do">Invite friends</a></li>
      </c:if>
    </ul>
</li>