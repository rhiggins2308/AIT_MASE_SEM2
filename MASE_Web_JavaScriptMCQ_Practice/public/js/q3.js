window.onload = function() {
	var content = document.getElementsByTagName('body')[0];
	var node;
	var displayString = '';
	for (var i=0; i < content.childNodes.length; i++) {
		node = content.childNodes[i];
		displayString += node.nodeName + ":" + node.nodeValue + '\n'
	}
	
	alert(displayString);
}