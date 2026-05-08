const prompt = require('prompt-sync')({ sigint: true });

function mediaPonderada(prova, trabalho, participacao) {
    let media = ((prova * 4) + (trabalho * 3) + (participacao * 3)) / 10;
    return media.toFixed(1);
}

let prova = Number(prompt("Nota da prova: "));
let trabalho = Number(prompt("Nota do trabalho: "));
let participacao = Number(prompt("Nota de participacao: "));
console.log(`Media final: ${mediaPonderada(prova, trabalho, participacao)}`);
