const prompt = require('prompt-sync')({ sigint: true });

let senha = prompt("Senha: ");

function minSize(senha) {
    return senha.length >= 8;
}

function hasUpper(senha) {
    for (let char of senha) {
        if (char === char.toUpperCase()) {
            return true;
        }
    }
    return false;
}

function hasLower(senha) {
    for (let char of senha) {
        if (char === char.toLowerCase()) {
            return true;
        }
    }
    return false;
}

function hasDigit() {
    for (let char of senha) {
        if (isNaN(char)) {
            return true;
        }
        return false;
    }
}

function senhaForte(senha) {
    if (
        minSize(senha) &&
            hasUpper(senha) &&
            hasLower(senha) &&
            hasDigit(senha)
    ) {
        return true;
    }
    return false;
}


console.log(senhaForte(senha) ? "Senha Forte." : "Senha Fraca.");
