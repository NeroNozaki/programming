package biblioteca;
import java.util.ArrayList;
import java.time.LocalDate;
// import java.time.temporal.ChronoUnit;

public class Membro {
    final private String nome;
    final private String email;
    final private LocalDate cadastroData;
    private Categoria categoria;
    private final ArrayList<Emprestimo> emprestimos = new ArrayList<>();
    private final ArrayList<Emprestimo> histEmprestimos = new ArrayList<>();

    Membro(String nome, String email, LocalDate cadastroData, String categoria) {
        setCategoria(categoria);
        this.nome = nome;
        this.email = email;
        this.cadastroData = cadastroData;
    }

    void setCategoria(String categoria) {
        switch (categoria) {
            case "padrao":
            this.categoria = new Padrao();
            break;

            case "premium":
            this.categoria = new Premium();
            break;
            case "vip":
            this.categoria = new VIP();
            break;
            default:
            throw new IllegalArgumentException("categoria de membro invalida.");
        }
    }

    void adicionarEmprestimo(Emprestimo emprestimo) {
        emprestimos.add(emprestimo);
    }

    void removerEmprestimo(Emprestimo emprestimo) {
        emprestimos.remove(emprestimo);
        histEmprestimos.add(emprestimo);
    }

    Categoria getCategoria() { return categoria; }
    ArrayList<Emprestimo> getEmprestimos() { return emprestimos; }
    String getNome() { return nome; }
    boolean temMulta(LocalDate dataAtual) {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getMulta(dataAtual) != 0) return true;
        }
        return false;
    }

    boolean temEmprestimo(Emprestimo emprestimo) {
        for (Emprestimo emp : emprestimos) {
            if (emp.getId() == emprestimo.getId()) return true;
        }
        return false;
    }
}
