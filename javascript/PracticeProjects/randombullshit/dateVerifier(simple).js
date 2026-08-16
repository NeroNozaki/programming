const prompt = require('prompt-sync')({ sigint: true });

const day = Number(prompt("Day: "));
let month = Number(prompt("Month: "));
const year = Number(prompt("Year: "));

let leap;
if (year % 4 === 0) {
    if (year % 100 === 0) {
        if (year % 400 === 0) {
            leap = true;
        } else {
            leap = false;
        }
    } else {
        leap = true;
    }
} else {
    leap = false;
}

let max;
if (month === 2) {
    max = leap ? 29 : 28;
} else if (month === 4 ||
           month === 6 ||
           month === 9 ||
           month === 11
          )
{
    max = 30;
} else {
    max = 31;
}

if (!(month >= 10)) {
    month = `0${month}`;
}

if (month <= 12 && day <= max) {
    console.log(`${day}/${month}/${year} is a valid date.`);
} else {
    console.log(`${day}/${month}/${year} is an invalid date.`);
}
