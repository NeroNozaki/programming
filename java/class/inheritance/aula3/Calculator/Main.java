package Calculator;

public class Main {
    public static void main(String[] args) {
        somar(34, 35);
        somar(34.0, 35.0);
        somar(34.5);
    }

    static void somar(int a, int b) {
        System.out.println("somar 1: " + (a + b));
    }
    static void somar(double a, double b) {
        System.out.println("somar 2: " + (a + b));
    }
    static void somar(double x) {
        System.out.println("somar 3: " + (x + x));
    }
}
