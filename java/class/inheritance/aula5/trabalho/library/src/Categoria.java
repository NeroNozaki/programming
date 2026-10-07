abstract class Categoria {
    private int maxLivros;
    int prazo_maximo;
    double multa;
    double discount = 1;
    boolean pode_renovar;

    int getMaxLivros() {
        return maxLivros;
    }
}

final class Padrao extends Categoria {
    int maxLivros = 3;
    int prazo_maximo = 14;
    double multa = 2.0;
    boolean pode_renovar = false;
}

class Premium extends Categoria {
    int maxLivros = 5;
    int prazo_maximo = 21;
    double multa = 1.5;
    boolean pode_renovar = true;
}

class VIP extends Categoria {
    int maxLivros = 10;
    int prazo_maximo = 30;
    double multa = 1.0;
    double discount = 0.8;
    boolean pode_renovar = true;
}
