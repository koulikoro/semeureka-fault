<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tt"%>
<tt:frame>
	<form class="form-inline pull-right form-group">
		<button type="button" class="btn btn-default btn-sm" data-toggle="modal" data-target="#create">添加</button>
	</form>
	<table class="table table-striped table-bordered">
		<tr>
			<th class="col-xs-1">序号</th>
			<th>安装位置</th>
			<th>组号</th>
			<th>所属线路</th>
		</tr>
		<c:forEach items="${groups}" var="group" varStatus="status">
			<tr>
				<td>${status.count}</td>
				<td><a href="${ctx}/group/${group.id}" data-toggle="modal" data-target="#update">${group.location}</a></td>
				<td>${group.number}</td>
				<td>${group.line.name}</td>
			</tr>
		</c:forEach>
	</table>
	<div id="create" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form id="group-create" action="${ctx}/group" method="post" class="form-horizontal">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">设备信息</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label class="col-md-2 control-label">安装位置</label>
							<div class="col-md-10">
								<input type="text" name="location" class="form-control" required maxlength="15" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">主机链路</label>
							<div class="col-md-10">
								<input type="text" name="hostCode" class="form-control" required
									pattern="^[0-9A-F]{4}|[0-9A-F]{12}$" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">终端编码</label>
							<div class="col-md-10">
								<div class="row">
									<c:forEach items="${phases}" var="phase">
										<div class="col-md-3">
											<input type="text" name="devices[${phase}].code" class="form-control"
												pattern="^[0-9A-F]{4}$" remote="${ctx}/device/contains" placeholder="${phase}相">
										</div>
									</c:forEach>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">所属线路</label>
							<div class="col-md-10">
								<select name="line.id" class="form-control" required>
									<c:forEach items="${lineService.findAll()}" var="line">
										<option value="${line.id}">${line.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="submit" class="btn btn-primary">添加</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div id="update" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content panel-default"></div>
		</div>
	</div>
	<script type="text/javascript">
		$('#group-create').validate();
	</script>
</tt:frame>