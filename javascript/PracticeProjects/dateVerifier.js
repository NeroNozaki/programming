const def = "13/05/2026";
const date = def ? def : prompt("Date: ");

const day = Number(date.slice(0, 2));
const month = Number(date.slice(3, 5));
const year = Number(date.slice(6));

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

if (month <= 12 && day <= max) {
    console.log(`${date} is a valid date.`);
} else {
    console.log(`${date} is an invalid date.`);
}
