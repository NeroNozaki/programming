let remainder1, remainder2;

function stuff(number) {
    remainder1 = number % 3;    
    remainder2 = number % 5;

    if (remainder1 == 0.0 && remainder2 == 0.0) {
	return `FizzBuzz`;
    } else if (remainder1 == 0.0) {
	return `Fizz`;
    } else if (remainder2 == 0.0) {
	return `Buzz`;
    } else {
	return number;	
    }
}

let list = [];

for (let i = 0; i < 100; i++) {
    list.push(i+1);
} 

let results = list.map(stuff);
console.log(results);
