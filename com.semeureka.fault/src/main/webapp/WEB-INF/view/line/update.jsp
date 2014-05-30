<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal-header panel-heading">
	<button type="button" class="close" data-dismiss="modal">&times;</button>
	<h4 class="modal-title">线路信息</h4>
</div>
<form id="line-update" action="${ctx}/line/${line.id}" method="post" class="form-horizontal">
	<div class="modal-body">
		<div class="form-group">
			<label class="col-md-3 control-label">名称</label>
			<div class="col-md-9">
				<input type="text" name="name" value="${line.name}" class="form-control" required maxlength="15" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">所属变电站</label>
			<div class="col-md-9">
				<select name="station.id" class="form-control" required>
					<c:forEach items="${stationService.findAll()}" var="station">
						<option value="${station.id}" ${line.station eq station ? 'selected' : ''}>${station.name}</option>
					</c:forEach>
				</select>
			</div>
		</div>
	</div>
	<div class="modal-footer">
		<a href="${ctx}/line/${line.id}/delete" class="btn btn-primary pull-left">删除</a>
		<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		<button type="submit" class="btn btn-primary">更新</button>
	</div>
</form>
<script type="text/javascript">
	$("#line-update").validate();
</script>