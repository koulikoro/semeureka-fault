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
		</tr>
		<c:forEach items="${stations}" var="station" varStatus="status">
			<tr>
				<td>${status.count}</td>
				<td><a href="${ctx}/station/${station.id}" data-toggle="modal" data-target="#update">${station.name}</a></td>
			</tr>
		</c:forEach>
	</table>
	<div id="create" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form id="station-create" action="${ctx}/station" method="post" class="form-horizontal">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">变电站信息</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label class="col-md-2 control-label">名称</label>
							<div class="col-md-10">
								<input type="text" name="name" class="form-control" required maxlength="15" />
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
		$("#station-create").validate();
	</script>
</tt:frame>