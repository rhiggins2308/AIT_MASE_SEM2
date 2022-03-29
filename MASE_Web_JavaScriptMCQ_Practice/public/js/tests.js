function leaky() {
	var local = 1;
	global = 2;
	
	console.log("local: " + local);
	console.log("global: " + global);
}

leaky();

/******************************************/

function prison() {
	prisoner_1 = 'I have escaped';
	var prisoner_2 = 'I am locked in!';
}

prison();
//console.log(prisoner_1);
//console.log(prisoner_2);


/******************************************/

function prison() {
	for (i = 0; i < 10; i++) {
		// ..
	}
}

prison();
console.log(i);

/******************************************/

function prison() {
	for (var i = 0; i < 10; i++) {
		// ..
	}
}

prison();
console.log(i);


