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

    LocalDate getDataAtual() { return dataAtual; }
    void avancarDia() { dataAtual = dataAtual.plusDays(1); }

    void registrarLivro(String titulo, String autor, int quantidadeTotal) {
        livros.add(new Livro(titulo, autor, quantidadeTotal));
    }

    void emprestarLivro(Membro membro, Livro livro) {
        livro.emprestarCopia();
        Emprestimo emprestimo = new Emprestimo(membro, livro, dataAtual);
        emprestimos.add(emprestimo);
        membro.adicionarEmprestimo(emprestimo); 
    }

    void receberLivro(Membro membro, Emprestimo emprestimo) {
        if (!emprestimo.isAtivo()) {
            throw new IllegalArgumentException("este emprestimo nao esta ativo.");
        }
        if (emprestimo.getMembro() != membro) {
            throw new IllegalArgumentException("este emprestimo nao pertence a este membro.");
        }
        Emprestimo registrado = null;
        for (Emprestimo emp : emprestimos) {
            if (emp.getId() == emprestimo.getId()) {
                registrado = emp;
                break;
            }
        }
        if (registrado == null) {
            throw new IllegalArgumentException("esse livro nao esta pendente para devolucao ou nao existe.");
        }

        registrado.getLivro().devolverCopia();
        registrado.finalizar();
        membro.removerEmprestimo(registrado);
        emprestimos.remove(registrado);
    }
}
