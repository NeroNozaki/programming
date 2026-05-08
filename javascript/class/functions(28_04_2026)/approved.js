const prompt = require('prompt-sync')({ sigint: true });

const av1 = Number(prompt("Qual sua nota da av1? "));
const av2 = Number(prompt("Qual sua nota da av2? "));

function approved(av1, av2) {
    const mp = (av1 + av2) / 2;
    if (mp < 4) {
        return false;
    }

    const av3 = Number(prompt("Qual sua nota da av3? "));
    const mf = (mp + av3) / 2;
    if (mf < 5) {
        return false;
    }
    return true;
}

if (approved(av1, av2)) {
    console.log("voce foi aprovado");
} else {
    console.log("voce foi reprovado");
}
