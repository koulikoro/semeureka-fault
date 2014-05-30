<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal-header panel-heading">
	<button type="button" class="close" data-dismiss="modal">&times;</button>
	<h4 class="modal-title">变电站信息</h4>
</div>
<form id="station-update" action="${ctx}/station/${station.id}" method="post"
	class="form-horizontal">
	<div class="modal-body">
		<div class="form-group">
			<label class="col-md-2 control-label" for="name">名称</label>
			<div class="col-md-10">
				<input id="name" type="text" name="name" value="${station.name}" class="form-control" required
					maxlength="15" />
			</div>
		</div>
	</div>
	<div class="modal-footer">
		<a href="${ctx}/station/${station.id}/delete" class="btn btn-primary pull-left">删除</a>
		<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		<button type="submit" class="btn btn-primary">更新</button>
	</div>
</form>
<script type="text/javascript">
	$("#station-update").validate();
</script>