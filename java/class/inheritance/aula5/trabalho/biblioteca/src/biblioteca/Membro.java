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
        histEmprestimos.add(emprestimo);
    }

    void removerEmprestimo(Emprestimo emprestimo) {
        emprestimos.remove(emprestimo);
    }

    Categoria getCategoria() { return categoria; }
    boolean podeFazerEmprestimo() {
        return emprestimos.size() < categoria.getLivrosMax();
    }
    String getNome() { return nome; }
    double getMultaTotal() {
        double multaTotal = 0;
        for (Emprestimo emprestimo : emprestimos) {
            multaTotal += emprestimo.getMulta();
        }
        return multaTotal;
    }
    boolean temMulta() {
        if (getMultaTotal() > 0) return true;
        return false;
    }

    boolean temEmprestimo(Emprestimo emprestimo) {
        for (Emprestimo emp : emprestimos) {
            if (emp.getId() == emprestimo.getId()) return true;
        }
        return false;
    }

    int getNumAtrasos() {
        int atrasos = 0;
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getDiasAtraso() > 0) {
                atrasos++;
            }
        }
        return atrasos;
    }
    int getNumEmprestimos() { return emprestimos.size(); }
    ArrayList<Livro> getAtrasos() {
        ArrayList<Livro> livros = new ArrayList<>();
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getDiasAtraso() > 0) {
                livros.add(emprestimo.getLivro());
            }
        }
        return livros;
    }
    ArrayList<Emprestimo> getHistEmprestimos() {
        ArrayList<Emprestimo> ultimosEmprestimos = new ArrayList<>();
        int inicio = Math.max(0, histEmprestimos.size() - 3);

        for (int i = histEmprestimos.size() - 1; i >= inicio; i--) {
            ultimosEmprestimos.add(histEmprestimos.get(i));
        }
        return ultimosEmprestimos;
    }

    void resumo() {
        System.out.println("Nome: " + nome);
        System.out.println("Email: " + email);
        System.out.println("Categoria: " + categoria);
        System.out.println("Quantidade de emprestimos: " + emprestimos.size());
        System.out.println("Multa: " + getMultaTotal());
        System.out.println("Numero de atrasos: " + getNumAtrasos());
    }
}
