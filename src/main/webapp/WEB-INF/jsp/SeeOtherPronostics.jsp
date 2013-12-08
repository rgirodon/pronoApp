<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script id="js">
	
	$(function() {

		$("#othersPronosticsForGameTable").tablesorter({

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
<div class="modal-dialog">
  <div class="modal-content">
    <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
      <h4 class="modal-title" id="seeOtherPronosticsModallLabel">What others think about <c:out value="${ game.label }" /></h4>
    </div>
    <div class="modal-body">
      <table id="othersPronosticsForGameTable" class="table table-striped table-condensed">
       	<thead>
       		<tr>
       			<th>User</th>
       			<th>Score</th>
       		</tr>
       	</thead>
       	<tfoot>
			<tr>
         			<th>User</th>
         			<th>Score</th>
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
	    	<c:forEach var="othersPronosticForGame" items="${othersPronosticsForGame}" >
	      		<tr>
          			<td><c:out value="${ othersPronosticForGame.login }" /></td>
          			<td><c:out value="${ othersPronosticForGame.pronosticScore }" /></td>
          		</tr>
	    	</c:forEach>
      	</tbody>
      </table>
    </div>
    <div class="modal-footer">
      <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
    </div>
  </div>
</div>