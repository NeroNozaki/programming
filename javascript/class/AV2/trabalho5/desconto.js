const prompt = require('prompt-sync')({ sigint: true });
const compras = [80, 250, 750, 1500];

function calcularDesconto(valor) {
    switch(true) {
    case valor >= 100 && valor < 500:
        return valor * 0.05;

    case valor >= 500 && valor < 1000:
        return valor * 0.10;

    case valor >= 1000:
        return valor * 0.15;

    default:
        return valor;
    }
}

function valorFinal(valor) {
    return valor - calcularDesconto(valor);
}

// let descontos = compras.map(calcularDesconto);
// let valores = compras.map(valorFinal);

for (let compra of compras) {
    console.log(`Compra: R$ ${compra.toFixed(2)} | Desconto: R$ ${calcularDesconto(compra).toFixed(2)} | Total a pagar: R$ ${valorFinal(compra).toFixed(2)}`);
}
