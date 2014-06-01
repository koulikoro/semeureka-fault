<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tt"%>
<tt:frame>
	<style type='text/css'>
.tree {
	margin-left: -40px;
}

.tree li {
	list-style-type: none;
	margin: 0;
	padding: 10px 5px 0 5px;
	position: relative
}

.tree li::before,.tree li::after {
	content: '';
	left: -20px;
	position: absolute;
	right: auto
}

.tree li::before {
	border-left: 1px solid #999;
	/* 	bottom: 50px; */
	height: 100%;
	top: 0;
	width: 1px
}

.tree li::after {
	border-top: 1px solid #999;
	height: 20px;
	top: 25px;
	width: 25px
}

.tree li span {
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	border: 1px solid #999;
	border-radius: 5px;
	display: inline-block;
	padding: 3px 8px;
	text-decoration: none
}

.tree li.parent_li>span {
	cursor: pointer
}

.tree>ul>li::before,.tree>ul>li::after {
	border: 0
}

.tree li:last-child::before {
	background-color: red;
	height: 30px
}

.tree li.parent_li>span:hover,.tree li.parent_li>span:hover+ul li span {
	background: #eee;
	border: 1px solid #94a0b4;
	color: #000
}

.well-xs {
	padding: 5px;
	border-radius: 3px;
}
</style>
	<script type='text/javascript'>
		$(function() {
			$(function() {
				$('.tree li:has(ul)').addClass('parent_li').find(' > span').attr('title', 'Collapse this branch');
				$('.tree li.parent_li > span').on(
						'click',
						function(e) {
							var children = $(this).parent('li.parent_li').find(' > ul > li');
							if (children.is(":visible")) {
								children.hide('fast');
								$(this).attr('title', 'Expand').find(' > i').addClass('glyphicon-plus-sign')
										.removeClass('glyphicon-minus-sign');
							} else {
								children.show('fast');
								$(this).attr('title', 'Collapse').find(' > i').addClass('glyphicon-minus-sign')
										.removeClass('glyphicon-plus-sign');
							}
							e.stopPropagation();
						});

			});
		});
	</script>
	<div class="tree">
		<ul>
			<c:forEach items="${stationService.findAll()}" var="station">
				<li><a class="well well-sm"><i class="glyphicon glyphicon-folder-open"></i>
						${station.name}</a>
					<ul>
						<c:forEach items="${station.lines}" var="line">
							<li><a class="well well-sm"><i class="glyphicon glyphicon-minus-sign"></i> ${line.name}</a>
								<ul>
									<c:forEach items="${line.groups}" var="group">
										<li><a class="well well-sm"><i class="glyphicon glyphicon-minus-sign"></i> ${group.location}</a></li>
									</c:forEach>
								</ul></li>
						</c:forEach>
					</ul></li>
			</c:forEach>
		</ul>
	</div>
</tt:frame>