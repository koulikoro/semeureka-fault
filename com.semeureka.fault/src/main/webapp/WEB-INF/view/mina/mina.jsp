<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tt"%>
<tt:frame>
	<ul class="nav nav-tabs">
		<li><a href="#server" data-toggle="tab">主站模式</a></li>
		<li><a href="#client" data-toggle="tab">子站模式</a></li>
		<li><a href="#serial" data-toggle="tab">串口模式</a></li>
	</ul>
	<div class="tab-content">
		<div id="server" class="tab-pane fade">
			<form action="${ctx}/mina/server" method="post" class="form-horizontal">
				<div class="form-group">
					<label class="col-sm-2 control-label">运行状态</label>
					<p class="col-sm-4 form-control-static ${server.active ? 'text-success' : 'text-warning' }">
						${server.active ? '已启动' : (serverStart ? '启动失败, 稍后自动重试' : '已停止')}
						<c:if test="${!empty serverError}">
							<span class="glyphicon glyphicon-warning-sign" title="${serverError.message}"></span>
						</c:if>
					</p>
					<label class="col-sm-2 control-label">连接数</label>
					<p class="col-sm-4 form-control-static">${server.managedSessionCount}</p>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">接收数据</label>
					<p class="col-sm-4 form-control-static">
						<fmt:formatNumber value="${server.statistics.readBytes / 1024}" pattern="#,##0.00KB" />
					</p>
					<label class="col-sm-2 control-label">发送数据</label>
					<p class="col-sm-4 form-control-static">
						<fmt:formatNumber value="${server.statistics.writtenBytes / 1024}" pattern="#,##0.00KB" />
					</p>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">监听地址</label>
					<div class="col-sm-4">
						<c:set var="address" value="${server.defaultLocalAddress}" />
						<input type="text" name="address" ${server.active ? 'readonly' : '' }
							value="${address.hostName}${empty address ? '' : ':'}${address.port}" class="form-control"
							placeholder="127.0.0.1:8811" required pattern="^\d+\.\d+\.\d+\.\d+:\d+$">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<a href="${ctx}/mina?tab=server" class="btn btn-default">刷新</a>
						<button type="submit" class="btn btn-primary">${server.active ? '停止' : '启动'}</button>
					</div>
				</div>
			</form>
		</div>
		<div id="client" class="tab-pane fade" id="client">
			<form action="${ctx}/mina/client" method="post" class="form-horizontal">
				<div class="form-group">
					<label class="col-sm-2 control-label">运行状态</label>
					<p class="col-sm-4 form-control-static ${client.active ? 'text-success' : 'text-warning' }">
						${client.active ? '已启动' : (clientStart ? '启动失败, 稍后自动重试' : '已停止')}
						<c:if test="${!empty clientError}">
							<span class="glyphicon glyphicon-warning-sign" title="${clientError.message}"></span>
						</c:if>
					</p>
					<label class="col-sm-2 control-label">连接数</label>
					<p class="col-sm-4 form-control-static">${client.managedSessionCount}</p>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">接收数据</label>
					<p class="col-sm-4 form-control-static">
						<fmt:formatNumber value="${client.statistics.readBytes / 1024}" pattern="#,##0.00KB" />
					</p>
					<label class="col-sm-2 control-label">发送数据</label>
					<p class="col-sm-4 form-control-static">
						<fmt:formatNumber value="${client.statistics.writtenBytes / 1024}" pattern="#,##0.00KB" />
					</p>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">连接地址</label>
					<div class="col-sm-4">
						<c:set var="address" value="${client.defaultRemoteAddress}" />
						<input type="text" name="address" ${client.active ? 'readonly' : '' }
							value="${address.hostName}${empty address ? '' : ':'}${address.port}" class="form-control"
							placeholder="127.0.0.1:8811" required pattern="^\d+\.\d+\.\d+\.\d+:\d+$">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<a href="${ctx}/mina?tab=client" class="btn btn-default">刷新</a>
						<button type="submit" class="btn btn-primary">${client.active ? '停止' : '启动'}</button>
					</div>
				</div>
			</form>
		</div>
		<div id="serial" class="tab-pane fade">
			<form action="${ctx}/mina/serial" method="post" class="form-horizontal">
				<div class="form-group">
					<label class="col-sm-2 control-label">运行状态</label>
					<p class="col-sm-4 form-control-static ${serial.active ? 'text-success' : 'text-warning' }">
						${serial.active ? '已启动' : (serialStart ? '启动失败, 稍后自动重试' : '已停止')}
						<c:if test="${!empty serialError}">
							<span class="glyphicon glyphicon-warning-sign" title="${serialError.message}"></span>
						</c:if>
					</p>
					<label class="col-sm-2 control-label">连接数</label>
					<p class="col-sm-4 form-control-static">${serial.managedSessionCount}</p>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">接收数据</label>
					<p class="col-sm-4 form-control-static">
						<fmt:formatNumber value="${serial.statistics.readBytes / 1024}" pattern="#,##0.00KB" />
					</p>
					<label class="col-sm-2 control-label">发送数据</label>
					<p class="col-sm-4 form-control-static">
						<fmt:formatNumber value="${serial.statistics.writtenBytes / 1024}" pattern="#,##0.00KB" />
					</p>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">串口地址</label>
					<div class="col-sm-4">
						<c:set var="address" value="${serial.defaultRemoteAddress}" />
						<select name="address" ${serial.active ? 'readonly' : '' } class="form-control" required>
							<c:forEach items="${commPosts}" var="commPost">
								<option value="${commPost.name}" ${commPost.name eq address.name ? 'selected' : ''}>${commPost.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<a href="${ctx}/mina?tab=serial" class="btn btn-default">刷新</a>
						<button type="submit" class="btn btn-primary">${serial.active ? '停止' : '启动'}</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<c:set var="tab" value="${empty param.tab ? 'server' : param.tab}" />
	<script type="text/javascript">
		$(function() {
			$('.nav-tabs a[href="#${tab}"]').tab('show');
		});
		$("#server form").validate();
		$("#client form").validate();
		$("#serial form").validate();
	</script>
</tt:frame>