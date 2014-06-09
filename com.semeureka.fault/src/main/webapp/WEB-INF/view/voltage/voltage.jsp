<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tt"%>
<tt:frame>
	<form id="query" action="${ctx}/voltage" class="form-inline pull-right">
		<jsp:include page="/WEB-INF/view/voltage/select.jsp" />
		<button type="submit" class="btn btn-default btn-sm">查询</button>
	</form>
	<table class="table table-striped table-bordered table-condensed">
		<tr>
			<th class="col-xs-1">序号</th>
			<th>位置</th>
			<th>组号</th>
			<th>相别</th>
			<th>自检电压(V)</th>
			<th>接收时间</th>
		</tr>
		<c:forEach items="${voltages.content}" var="voltage" varStatus="status">
			<tr>
				<td>${voltages.number * voltages.size + status.count}</td>
				<td>${voltage.group.location}</td>
				<td>${voltage.group.number}</td>
				<td>${voltage.phase}</td>
				<td><fmt:formatNumber value="${voltage.value / 1000}" pattern="0.0" /></td>
				<td><fmt:formatDate value="${voltage.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
			</tr>
		</c:forEach>
	</table>
	<tt:page page="${voltages}" form="#query" />
</tt:frame>