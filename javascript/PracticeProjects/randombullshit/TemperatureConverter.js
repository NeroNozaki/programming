const prompt = require('prompt-sync')({ sigint: true });

let c, f;

console.log("This program will convert Celsius to Fahrenheit or vice versa");
let t = prompt("Which would you like to convert? (C or F) ").trim().toLowerCase();

switch (t) {
case "c":
    c = Number(prompt("What is your temperature in Celcius? "));
    if (isNaN(c)) {
	console.log("This is not a number.");
    } else {
	f = ((c * 9) / 5) + 32;
	console.log(`Your temperature in Fahrenheit is ${f}°.`);
    }
    break;
case "f":
    f = Number(prompt("What is your temperature in Fahrenheit? "));
    if (isNaN(f)) {
	console.log("This is not a number.");
    } else {
	c = ((f - 32) * 5) / 9;
	console.log(`Your temperature in Celsius is ${c}°.`);
    }
    break;
default:
    console.log(`That is not a valid temperature type.`);
}


// c = ((f - 32) * 5) / 9
// f = ((c * 9) / 5) + 32
