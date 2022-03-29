window.onload = function() {
	var bodyNode = document.getElementsByTagName("body")[0];
	var nodes = bodyNode.childNodes.length;
	console.log(nodes);
		
	for (var i = 0; i < nodes; i++) {
		console.log(bodyNode.childNodes[i]);		
	}
}