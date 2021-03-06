<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tt"%>
<tt:frame>
	<form id="query" action="${ctx}/fault" class="form-inline pull-right">
		<jsp:include page="/WEB-INF/view/voltage/select.jsp" />
		<button type="submit" class="btn btn-default btn-sm">查询</button>
	</form>
	<table class="table table-striped table-bordered table-condensed">
		<tr>
			<th class="col-xs-1">序号</th>
			<th>位置</th>
			<th>组号</th>
			<th>故障类型</th>
			<th>相别</th>
			<th>接收时间</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${faults.content}" var="fault" varStatus="status">
			<tr>
				<td>${faults.number * faults.size + status.count}</td>
				<td>${fault.group.location}</td>
				<td>${fault.group.number}</td>
				<td><fmt:message key="${fault.warnType}" /></td>
				<td>${fault.phases}</td>
				<td><fmt:formatDate value="${fault.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td><a href="${ctx}/fault/${fault.id}"
					class="label label-${empty fault.alert.comment ? 'warning' : 'info'}" data-toggle="modal"
					data-target="#update">${empty fault.alert.comment ? '处理' : '详情'}</a></td>
			</tr>
		</c:forEach>
	</table>
	<tt:page page="${faults}" form="#query" />
	<div id="update" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content panel-default"></div>
		</div>
	</div>
</tt:frame>