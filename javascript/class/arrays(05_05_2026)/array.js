let array = [];

for (let i = 1; i <= 10; i++) {
    array.push(i**2);
}
let sum = 0;
for (let i = 0; i < array.length; i++) {
    sum += array[i];
}

console.log(sum);
