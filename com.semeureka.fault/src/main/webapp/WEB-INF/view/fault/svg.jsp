<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tt"%>
<tt:frame>
	<div class="well">
		<embed id="svg" src="${ctx}/resources/svg/2.svg" type="image/svg+xml" wmode="transparent"
			style="width: 100%;" />
	</div>
	<a onclick="change()">Click</a>
	<script type="text/javascript">
		function change() {
			var svg = $('.well')[0];
			svg.innerHTML = '<embed id="svg" src="${ctx}/resources/svg/3.svg" type="image/svg+xml" wmode="transparent" style="width: 100%;" />';
			svgInit();
		}
		var ie678 = !$.support.leadingWhitespace;
		var svgRoot, svgBox;
		function svgInit() {
			var $svg = $('embed[type="image/svg+xml"]');
			try {
				svgDoc = $svg[0].getSVGDocument();
				svgRoot = svgDoc.documentElement;
				svgBox = svgRoot.getBBox();
			} catch (e) {
				setTimeout(svgInit, 50);
				return;
			}
			$svg[0].onmousewheel = handleMouseWheel;
			var sc = $svg.width() / svgBox.width;
			$svg.height(svgBox.height * (sc < 1 ? sc : 1));
			svgBox = svgRoot.getBBox();
			svgRoot.setAttribute('viewBox', svgBox.x + ' ' + svgBox.y + ' ' + svgBox.width + ' ' + svgBox.height);
			svgRoot.setAttribute('preserveAspectRatio', 'xMidYMin');
			svgRoot.setAttribute('onmousedown', 'handleMouseDown(evt)');
			svgRoot.setAttribute('onmouseup', 'handleMouseUp(evt)');
			svgRoot.setAttribute('onmousemove', 'handleMouseMove(evt)');
			svgRoot.setAttribute('onmousemoveover', 'handleMouseMoveOver(evt)');
			svgRoot.setAttribute('onmousemoveout', 'handleMouseMoveOut(evt)');
			var node = svgDoc.createElement('rect');
			node.setAttribute('x', svgBox.x - svgBox.width);
			node.setAttribute('y', svgBox.y - svgBox.height);
			node.setAttribute('width', svgBox.width * 3);
			node.setAttribute('height', svgBox.height * 3);
			node.setAttribute('fill-opacity', 0);
			if (svgRoot.firstChild) {
				svgRoot.insertBefore(node, svgRoot.firstChild);
			} else {
				svgRoot.appendChild(node);
			}
		}
		$(window).load(svgInit);
	</script>
	<script src="${ctx}/resources/svg/svg.js"></script>
</tt:frame>