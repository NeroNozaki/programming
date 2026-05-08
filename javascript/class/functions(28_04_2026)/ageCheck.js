const prompt = require('prompt-sync')({ sigint: true });

let age = Number(prompt("How old are you? "));

function checkAgeRange(age) {
    if (age >= 60) {
        console.log("You are old.");
    } else if (age >= 18) {
        console.log("You are an adult.");
    } else {
        console.log("You are a child. leave.");
    }
}

checkAgeRange(age);
