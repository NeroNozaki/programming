package biblioteca;

abstract class Categoria {
    int livrosMax;
    int prazoMax;
    double multa;
    double desconto = 1;
    boolean podeRenovar;

    int getLivrosMax() { return this.livrosMax; }
    int getPrazoMax() { return this.prazoMax; }
    double getMulta() { return this.multa; }
    double getDesconto() { return this.desconto; }
    boolean getPodeRenovar() { return this.podeRenovar; }
}

final class Padrao extends Categoria {
    private int livrosMax = 3;
    private int prazoMax = 14;
    private double multa = 2.0;
    private boolean podeRenovar = false;
}

class Premium extends Categoria {
    private int livrosMax = 5;
    private int prazoMax = 21;
    private double multa = 1.5;
    private boolean podeRenovar = true;
}

class VIP extends Categoria {
    private int livrosMax = 10;
    private int prazoMax = 30;
    private double multa = 1.0;
    private double discount = 0.8;
    private boolean podeRenovar = true;
}
