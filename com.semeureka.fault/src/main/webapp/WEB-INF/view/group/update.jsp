<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="modal-header panel-heading">
	<button type="button" class="close" data-dismiss="modal">&times;</button>
	<h4 class="modal-title">设备信息</h4>
</div>
<form id="group-update" action="${ctx}/group/${group.id}" method="post" class="form-horizontal">
	<div class="modal-body">
		<div class="form-group">
			<label class="col-md-2 control-label">位置</label>
			<div class="col-md-10">
				<input type="text" name="location" value="${group.location}" class="form-control" required
					maxlength="15" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">组号</label>
			<div class="col-md-4">
				<input type="text" name="number" value="${group.number}" class="form-control" required
					pattern="^\d+$" />
			</div>
			<label class="col-md-2 control-label">设备类型</label>
			<div class="col-md-4">
				<select name="groupType" class="form-control" required>
					<c:forEach items="${groupTypes}" var="groupType">
						<option value="${groupType}" ${group.groupType eq groupType ? 'selected' : ''}><fmt:message
								key="${groupType}" /></option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">所属线路</label>
			<div class="col-md-4">
				<select name="line.id" class="form-control" required>
					<c:forEach items="${lineService.findAll()}" var="line">
						<option value="${line.id}" ${group.line eq line ? 'selected' : ''}>${line.name}</option>
					</c:forEach>
				</select>
			</div>
			<label class="col-md-2 control-label">前部设备</label>
			<div class="col-md-4">
				<select name="parent.id" class="form-control">
					<option value="">无</option>
					<c:forEach items="${parents}" var="parent">
						<option value="${parent.id}" ${group.parent eq parent ? 'selected' : ''}>${group.location}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">主机链路</label>
			<div class="col-md-10">
				<input type="text" name="hostCodeHex" value="${group.hostCodeHex}" class="form-control" required
					pattern="^[0-9A-F]{4}|[0-9A-F]{12}$" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">终端编码</label>
			<div class="col-md-10">
				<div class="row">
					<c:forEach items="${phases}" var="phase">
						<div class="col-md-3">
							<input type="hidden" name="devices[${phase}].id" value="${group.devices[phase].id}">
							<input type="text" name="devices[${phase}].codeHex" value="${group.devices[phase].codeHex}"
								class="form-control device-code" pattern="^[0-9A-F]{4}$" notEqual=".device-code"
								remote="${ctx}/device/contains?id=${group.id}" placeholder="${phase}相">
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	<div class="modal-footer">
		<a href="${ctx}/group/${group.id}/delete" class="btn btn-primary pull-left">删除</a>
		<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		<button type="submit" class="btn btn-primary">更新</button>
	</div>
</form>
<script type="text/javascript">
	$('#group-update').validate();
</script>