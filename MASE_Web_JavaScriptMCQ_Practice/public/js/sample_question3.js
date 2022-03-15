var hi = 'hello';
function doaJob() {
	console.log(hi);
	var hi = 'Goodbye';
	console.log(hi);
}

doaJob();
console.log(hi);