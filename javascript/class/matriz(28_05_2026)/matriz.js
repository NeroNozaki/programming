let mat = [];
let mat2 = [];
let sum = 0;

let number = 20;
let has = false;

for (let i = 0; i < 5; i++) {
    for (let j = 0; j < 5; j++) {
        mat2.push((j+1)+(5*i));
        sum += mat2[j];
        if (mat2[j] === number) has = true; 
    } 
    mat.push(mat2);
    mat2 = [];
}
 
console.log(has);
