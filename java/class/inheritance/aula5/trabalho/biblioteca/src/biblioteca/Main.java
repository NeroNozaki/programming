package biblioteca;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca1 = new Biblioteca(LocalDate.of(2026, 10, 10));
        System.out.println(biblioteca1.getDataAtual());
    }
}
