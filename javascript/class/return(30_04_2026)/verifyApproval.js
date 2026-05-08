const prompt = require('prompt-sync')({ sigint: true });

console.log(
    verifyApproval() ?
        "aprovado" :
        "reprovado"
);

function verifyApproval() {
    const av1 = Number(prompt("Qual sua nota da av1? "));
    const av2 = Number(prompt("Qual sua nota da av2? "));

    const mp = findPartialAvg(av1, av2);

    if (mp < 4) return false;

    const av3 = Number(prompt("Qual sua nota da av3? "));
    const att = Number(prompt("Qual a sua frequencia? "));

    const mf = findFinalAvg(mp, av3);
    return validateApproval(mf, att);
}

function findPartialAvg(av1, av2) {
    return (av1 + av2) / 2;
}

function findFinalAvg(mp, av3) {
    return (mp + av3) / 2;
}

function validateApproval(mf, att) {
    if (mf >= 5 && att >= 75) {
        return true;
    }
    return false;
}
