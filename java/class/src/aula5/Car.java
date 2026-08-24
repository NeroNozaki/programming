public class Car {
    private String marca;
    private String cor;
    private int ano;
    private String placa;
    private double peso;

    String getMarca() {
        return marca;
    }
    String getCor() {
        return cor;
    }
    int getAno() {
        return ano;
    }
    String getPlaca() {
        return placa;
    }
    double getPeso() {
        return peso;
    }

    void setMarca(String marca) {
        this.marca = marca;
    }
    void setCor(String cor) {
        this.cor = cor;
    }
    void setAno(int ano) {
        this.ano = ano;
    }
    void setPlaca(String placa) {
        this.placa = placa;
    }
    void setPeso(double peso) {
        this.peso = peso;
    }
}
