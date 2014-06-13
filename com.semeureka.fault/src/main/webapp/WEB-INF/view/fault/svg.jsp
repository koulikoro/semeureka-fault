<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tt"%>
<tt:frame>
	<div class="well">
		<embed id="svg" src="${ctx}/resources/svg/2.svg" type="image/svg+xml" wmode="transparent"
			style="width: 100%"></embed>
	</div>
	<!-- <object src="countryMap.svg" type="image/svg+xml"></object> -->
	<!-- <iframe src="countryMap.svg"></iframe> -->

	<script type="text/javascript">
		if (!$.support.leadingWhitespace) {
			// alert('IE6-8');
		}
		$('#svg').height($('#svg').width());
		$(window).load(function() {
			var svgDoc = document.getElementById("svg").getSVGDocument();
			var svgRoot = svgDoc.getDocumentElement();
			var svgBox = svgRoot.getBBox();
			$('#svg').height(svgBox.height + svgBox.y);
		});
		// 		$(function() {
		// 			alert(document.getElementById("svg").getSVGDocument());
		// 		});
	</script>
</tt:frame>