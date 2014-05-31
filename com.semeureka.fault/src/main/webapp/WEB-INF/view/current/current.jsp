<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tt"%>
<tt:frame>
	<form id="query" action="${ctx}/current" class="form-inline pull-right">
		<jsp:include page="/WEB-INF/view/voltage/select.jsp" />
		<button type="submit" class="btn btn-default btn-sm">查询</button>
	</form>
	<table class="table table-striped table-bordered table-condensed">
		<tr>
			<th class="col-xs-1">序号</th>
			<th>位置</th>
			<th>组号</th>
			<th>相别</th>
			<th>电流(A)</th>
			<th>温度(&deg;)</th>
			<th>接收时间</th>
		</tr>
		<c:forEach items="${currents.content}" var="current" varStatus="status">
			<tr>
				<td>${currents.number * currents.size + status.count}</td>
				<td>${current.group.location}</td>
				<td>${current.group.number}</td>
				<td>${current.phase}</td>
				<td>${current.value}</td>
				<td>${current.temperature}</td>
				<td><fmt:formatDate value="${current.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
			</tr>
		</c:forEach>
	</table>
	<tt:page page="${currents}" form="#query" />
</tt:frame>