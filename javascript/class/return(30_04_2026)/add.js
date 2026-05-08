const prompt = require('prompt-sync')({ sigint: true });

let a = 34;
let b = 35;

function add(a,b) {
    return a + b;
}

console.log(add(a,b));
