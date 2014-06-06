<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="form-group">
	<select name="group.line.id" class="form-control input-sm" onchange="lineChange()">
		<option value="">所有线路</option>
		<c:forEach items="${lineService.findAll()}" var="line">
			<option value="${line.id}" ${line eq example.group.line ? 'selected' : ''}>${line.name}</option>
		</c:forEach>
	</select>
</div>
<div class="form-group">
	<select name="group.id" class="form-control input-sm">
		<option value="">所有设备</option>
		<c:forEach items="${groupService.findAll(example.group)}" var="group">
			<option value="${group.id}" ${group eq example.group ? 'selected' : ''}>${group.location}</option>
		</c:forEach>
	</select>
</div>
<script type="text/javascript">
	function lineChange() {
		var lineId = $('[name="group.line.id"]').val();
		var url = '${ctx}/group?line.id=' + lineId;
		$.getJSON(url, function(json) {
			$('[name="group.id"] option:gt(0)').remove();
			for (var i = 0; i < json.length; i++) {
				$('<option/>').attr('value', json[i].id).attr('title', json[i].number).text(
						json[i].location).appendTo('[name="group.id"]');
			}
		});
	}
</script>