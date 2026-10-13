package biblioteca;

abstract class Categoria {
    private final int livrosMax;
    private final int prazoMax;
    private final double valorMulta;
    private final double descontoMulta;
    private final boolean podeRenovar;

    Categoria(int livrosMax, int prazoMax, double multa,
              double desconto, boolean podeRenovar) {
        this.livrosMax = livrosMax;
        this.prazoMax = prazoMax;
        this.valorMulta = multa;
        this.descontoMulta = desconto;
        this.podeRenovar = podeRenovar;
    }

    int getLivrosMax() { return livrosMax; }
    int getPrazoMax() { return prazoMax; }
    double getValorMulta() { return valorMulta; }
    double getDescontoMulta() { return descontoMulta; }
    boolean getPodeRenovar() { return podeRenovar; }

    double calcularMulta(long diasAtraso) {
        return Math.max(0, diasAtraso) * valorMulta * descontoMulta;
    }

    double calcularMulta(long diasAtraso, double descontoAdicional) {
        return calcularMulta(diasAtraso)
            * (1 - descontoAdicional / 100);
    }
}

final class Padrao extends Categoria {
    Padrao() {
        super(3, 14, 2.00, 1.0, false);
    }
}

final class Premium extends Categoria {
    Premium() {
        super(5, 21, 1.50, 1.0, true);
    }
}

final class VIP extends Categoria {
    VIP() {
        super(10, 30, 1.00, 0.8, true);
    }
}
