const prompt = require('prompt-sync')({ sigint: true });

let morse = ".... . .-.. .-.. --- /";
let text = "hello /";
const morseTable = {
    'A': '.-',    'B': '-...',  'C': '-.-.', 'D': '-..',
    'E': '.',     'F': '..-.',  'G': '--.',  'H': '....',
    'I': '..',    'J': '.---',  'K': '-.-',  'L': '.-..',
    'M': '--',    'N': '-.',    'O': '---',  'P': '.--.',
    'Q': '--.-',  'R': '.-.',   'S': '...',  'T': '-',
    'U': '..-',   'V': '...-',  'W': '.--',  'X': '-..-',
    'Y': '-.--',  'Z': '--..',

    '1': '.----', '2': '..---', '3': '...--', '4': '....-',
    '5': '.....', '6': '-....', '7': '--...', '8': '---..',
    '9': '----.', '0': '-----',
    ' ': '/'
};

const letterTable = {};

for (let [letter, code] of Object.entries(morseTable)) {
    letterTable[code] = letter;
}

function removerAcentos(texto) {
  return texto.normalize('NFD').replace(/[\u0300-\u036f]/g, "");
}

function encode(text) {
    let string = removerAcentos(text.trim().toUpperCase());
    let morse = "";
    for (let char of string) {
        if (morseTable[char]) {
            morse += morseTable[char]+" ";
        }
    }
    return morse;
}
function decode(morse) {
    let string = morse.trim().toUpperCase();
    let text = "";
    let letter = "";

    for (let i = 0; i < string.length; i++) {
        const char = string[i];
        
        if (char === " " || char === "\n") {
            if (letterTable[letter]) {
                text += letterTable[letter] ?? letter;
                letter = "";
            }
        }

        else if (char === "/") {
            text += " ";
            letter = "";
        }

        else {
            letter += char;   
        }
    }

    if (letter) {
        text += letterTable[letter] ?? letter;
    }

    return text;
}

console.log("(1 - Codificar)");
console.log("(2 - Decodificar)");

let choice = Number(prompt(""));

if (choice === 1) {
    console.log("Seu texto aqui:\n");
    let string = prompt("");
    console.log(encode(string));
} else if (choice === 2) {
    console.log("Seu texto aqui:\n");
    let string = prompt("");
    console.log(decode(string));
}
else {
    console.log("Escolha invalida.");
}
