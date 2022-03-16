var a = 'Hello';

function greet() {
	var b = 'World';
	console.log(a + ' ' + b);
	if (b == 'World') {
		var c = 'hello';
		console.log(a + ' ' + b + ' ' + c);
	}
	console.log(a + ' ' + b + ' ' + c);
}
greet();