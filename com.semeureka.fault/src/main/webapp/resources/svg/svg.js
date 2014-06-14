var root, startPoint, currentPoint;
function getRoot(evt) {
	return evt.target.ownerDocument.documentElement;
}
function handleMouseDown(evt) {
	if (evt.button == 0) {
		root = getRoot(evt);
		startPoint = root.createSVGPoint();
		startPoint.x = evt.clientX;
		startPoint.y = evt.clientY;
	}
}
function handleMouseUp(evt) {
	startPoint = null;
}
function handleMouseMove(evt) {
	if (startPoint) {
		root.currentTranslate.x += evt.clientX - startPoint.x;
		root.currentTranslate.y += evt.clientY - startPoint.y;
		startPoint = root.createSVGPoint();
		startPoint.x = evt.clientX;
		startPoint.y = evt.clientY;
		// currentPoint = root.createSVGPoint();
		// currentPoint.x = evt.clientX;
		// currentPoint.y = evt.clientY;
	}
}
function handleMouseOver(evt) {
}
function handleMouseOut(evt) {
}
function handleMouseWheel(evt) {
	evt = evt || window.event;
	if (evt.preventDefault) {
		evt.preventDefault();
	}
	evt.returnValue = false;
	// alert(evt.wheelDelta);
	var vb = svgRoot.getAttribute('viewBox');
	var vbs = vb.split(' ');
	var sc = evt.wheelDelta < 0 ? 9 / 8 : 8 / 9;
//	svgRoot.currentScale *= sc;
	svgRoot.setAttribute('viewBox', vbs[0] + ' ' + vbs[1] + ' ' + (vbs[2] * sc) + ' ' + (vbs[3] * sc));

}