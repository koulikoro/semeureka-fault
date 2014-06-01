<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tt"%>
<tt:frame>
	<ul class="nav nav-tabs">
		<li><a href="#acceptor" data-toggle="tab">主站模式</a></li>
		<li><a href="#connector" data-toggle="tab">子站模式</a></li>
		<li><a href="#serial" data-toggle="tab">串口模式</a></li>
	</ul>
	<div class="tab-content">
		<div id="acceptor" class="tab-pane fade">
			<form action="${ctx}/mina/acceptor" method="post" class="form-horizontal">
				<div class="form-group">
					<label class="col-sm-2 control-label">运行状态</label>
					<p class="col-sm-4 form-control-static ${acceptor.active ? 'text-success' : 'text-warning' }">${acceptor.active ? '已启动' : '已停止'}
						<c:if test="${!empty acceptorError}">
							<span class="glyphicon glyphicon-warning-sign" title="${acceptorError.message}"></span>
						</c:if>
					</p>
					<label class="col-sm-2 control-label">连接数</label>
					<p class="col-sm-4 form-control-static">${acceptor.managedSessionCount}</p>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">接收数据</label>
					<p class="col-sm-4 form-control-static">
						<fmt:formatNumber value="${acceptor.statistics.readBytes / 1024}" pattern="#,##0.00KB" />
					</p>
					<label class="col-sm-2 control-label">发送数据</label>
					<p class="col-sm-4 form-control-static">
						<fmt:formatNumber value="${acceptor.statistics.writtenBytes / 1024}" pattern="#,##0.00KB" />
					</p>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">监听地址</label>
					<div class="col-sm-4">
						<c:set var="address" value="${acceptor.defaultLocalAddress}" />
						<input type="text" name="defaultAddress" ${acceptor.active ? 'readonly' : '' }
							value="${address.hostName}${empty address ? '' : ':'}${address.port}" class="form-control"
							placeholder="127.0.0.1:8811" required pattern="^\d+\.\d+\.\d+\.\d+:\d+$">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<a  href="${ctx}/mina?tab=acceptor" class="btn btn-default">刷新</a>
						<button type="submit" class="btn btn-primary">${acceptor.active ? '停止' : '启动'}</button>
					</div>
				</div>
			</form>
		</div>
		<div id="connector" class="tab-pane fade" id="connector">
			<form action="${ctx}/mina/connector" method="post" class="form-horizontal">
				<div class="form-group">
					<label class="col-sm-2 control-label">运行状态</label>
					<p class="col-sm-4 form-control-static ${connector.active ? 'text-success' : 'text-warning' }">${connector.active ? '已启动' : '已停止'}
						<c:if test="${!empty connectorError}">
							<span class="glyphicon glyphicon-warning-sign" title="${connectorError.message}"></span>
						</c:if>
					</p>
					<label class="col-sm-2 control-label">连接数</label>
					<p class="col-sm-4 form-control-static">${connector.managedSessionCount}</p>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">接收数据</label>
					<p class="col-sm-4 form-control-static">
						<fmt:formatNumber value="${connector.statistics.readBytes / 1024}" pattern="#,##0.00KB" />
					</p>
					<label class="col-sm-2 control-label">发送数据</label>
					<p class="col-sm-4 form-control-static">
						<fmt:formatNumber value="${connector.statistics.writtenBytes / 1024}" pattern="#,##0.00KB" />
					</p>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">连接地址</label>
					<div class="col-sm-4">
						<c:set var="address" value="${connector.defaultRemoteAddress}" />
						<input type="text" name="defaultAddress" ${connector.active ? 'readonly' : '' }
							value="${address.hostName}${empty address ? '' : ':'}${address.port}" class="form-control"
							placeholder="127.0.0.1:8811" required pattern="^\d+\.\d+\.\d+\.\d+:\d+$">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<a  href="${ctx}/mina?tab=connector" class="btn btn-default">刷新</a>
						<button type="submit" class="btn btn-primary">${connector.active ? '停止' : '启动'}</button>
					</div>
				</div>
			</form>
		</div>
		<div id="serial" class="tab-pane fade">
			<form action="${ctx}/mina/serial" method="post" class="form-horizontal">
				<div class="form-group">
					<label class="col-sm-2 control-label">运行状态</label>
					<p class="col-sm-4 form-control-static ${serial.active ? 'text-success' : 'text-warning' }">${serial.active ? '已启动' : '已停止'}
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
						<select name="defaultAddress" ${serial.active ? 'disabled' : '' } class="form-control"
							required>
							<c:forEach items="${commPosts}" var="commPost">
								<option value="${commPost.name}" ${commPost.name eq address.name ? 'selected' : ''}>${commPost.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<a  href="${ctx}/mina?tab=serial" class="btn btn-default">刷新</a>
						<button type="submit" class="btn btn-primary">${serial.active ? '停止' : '启动'}</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<c:set var="tab" value="${empty param.tab ? 'acceptor' : param.tab}" />
	<script type="text/javascript">
		$(function() {
			$('.nav-tabs a[href="#${tab}"]').tab('show');
		});
		$("#acceptor form").validate();
		$("#connector form").validate();
		$("#serial form").validate();
	</script>
</tt:frame>