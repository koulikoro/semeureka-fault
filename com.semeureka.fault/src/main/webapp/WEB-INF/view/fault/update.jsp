<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal-header panel-heading">
	<button type="button" class="close" data-dismiss="modal">&times;</button>
	<h4 class="modal-title">故障信息</h4>
</div>
<form id="fault-update" action="${ctx}/fault/${fault.id}" method="post" class="form-horizontal">
	<div class="modal-body">
		<div class="form-group">
			<label class="col-md-2 control-label">位置</label>
			<div class="col-md-4">
				<p class="form-control-static">${fault.group.location}</p>
			</div>
			<label class="col-md-2 control-label">相别</label>
			<div class="col-md-4">
				<p class="form-control-static">${fault.phases}</p>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">所属线路</label>
			<div class="col-md-4">
				<p class="form-control-static">${fault.group.line.name}</p>
			</div>
			<label class="col-md-2 control-label">接收时间</label>
			<div class="col-md-4">
				<p class="form-control-static">${fault.createTime}</p>
			</div>

		</div>
		<c:if test="${!empty fault.alert.updateTime}">
			<div class="form-group">
				<label class="col-md-2 control-label">处理时间</label>
				<div class="col-md-10">
					<p class="form-control-static">
						<fmt:formatDate value="${fault.alert.updateTime}" pattern="yyyy-MM-dd HH-mm-ss" />
					</p>
				</div>
			</div>
		</c:if>
		<div class="form-group">
			<label class="col-md-2 control-label">处理措施</label>
			<div class="col-md-10">
				<textarea name="comment" class="form-control" rows="3" style="resize: none;" required
					maxlength="255">${fault.alert.comment}</textarea>
			</div>
		</div>
	</div>
	<div class="modal-footer">
		<a href="${ctx}/fault/${fault.id}/delete" class="btn btn-primary pull-left">删除</a>
		<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		<button type="submit" class="btn btn-primary">处理</button>
	</div>
</form>
<script type="text/javascript">
	$("#fault-update").validate();
</script>