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
			<th>名称</th>
			<th>所属变电站</th>
		</tr>
		<c:forEach items="${lines}" var="line" varStatus="status">
			<tr>
				<td>${status.count}</td>
				<td><a href="${ctx}/line/${line.id}" data-toggle="modal" data-target="#update">${line.name}</a></td>
				<td>${line.station.name}</td>
			</tr>
		</c:forEach>
	</table>
	<div id="create" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form id="line-create" action="${ctx}/line" method="post" class="form-horizontal">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">线路信息</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label class="col-md-3 control-label">名称</label>
							<div class="col-md-9">
								<input type="text" name="name" class="form-control" required maxlength="15" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">所属变电站</label>
							<div class="col-md-9">
								<select name="station.id" class="form-control" required>
									<c:forEach items="${stationService.findAll()}" var="station">
										<option value="${station.id}">${station.name}</option>
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
		$("#line-create").validate();
	</script>
</tt:frame>