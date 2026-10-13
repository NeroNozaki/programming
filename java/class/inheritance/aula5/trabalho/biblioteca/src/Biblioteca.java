package biblioteca;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Biblioteca {
    private LocalDate dataAtual;
    private ArrayList<Membro> membros = new ArrayList<>();
    private ArrayList<Emprestimo> emprestimos = new ArrayList<>();
    private ArrayList<Livro> livros = new ArrayList<>();

    private double totalMultas;

    Biblioteca(LocalDate dataInicial) {
        this.dataAtual = dataInicial;
    }

    void registrarLivro(String titulo, String autor, int quantidadeTotal) {
        livros.add(new Livro(titulo, autor, quantidadeTotal));
    }

    LocalDate getDataAtual() { return dataAtual; }
    void avancarDia() { dataAtual = dataAtual.plusDays(1); }

    void emprestarLivro(Membro membro, Livro livro) {
        livro.emprestarCopia();
        Emprestimo emprestimo = new Emprestimo(membro, livro, dataAtual);
        emprestimos.add(emprestimo);
        membro.adicionarEmprestimo(emprestimo); 
    }
    
    void devolverLivro(Membro membro, Emprestimo emprestimo) {
        Emprestimo emprestimoADevolver;
        for (Emprestimo emp : emprestimos) {
            if (emp.id() == emprestimo.id()) {
                emprestimoADevolver = emp;
            }
        }
        if (emprestimoADevolver != null) {
            emprestimoADevolver.livro().devolverCopia();
            emprestimos.remove(emprestimoADevolver);
        } else {
            throw new IllegalArgumentException("esse livro nao esta pendente para devolucao ou nao existe.");
        }
    }
}
