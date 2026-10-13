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

    void avancarDia() { dataAtual = dataAtual.plusDays(1); }

    void registrarMembro(String nome, String email, String categoria) {
        membros.add(new Membro(nome, email, dataAtual, categoria));
    }
    void registrarMembro(Membro membro) {
        membros.add(membro);
    }
    void registrarLivro(String titulo, String autor, int quantidadeTotal) {
        livros.add(new Livro(titulo, autor, quantidadeTotal));
    }
    void registrarLivro(Livro livro) { livros.add(livro); }

    void emprestarLivro(Membro membro, Livro livro) {
        Emprestimo emprestimo = new Emprestimo(membro, livro, dataAtual);
        livro.emprestarCopia();
        emprestimos.add(emprestimo);
        membro.adicionarEmprestimo(emprestimo);
    }

    private void checarDevolucao(Membro membro, Emprestimo emprestimo) {
        if (membro.temMulta(dataAtual)) {
            throw new IllegalArgumentException("nao pode devolver nenhum livro enquanto voce tem um emprestimo atrasado.");
        }
        if (!emprestimo.isAtivo()) {
            throw new IllegalArgumentException("este emprestimo nao esta ativo.");
        }
        if (emprestimo.getMembro() != membro) {
            throw new IllegalArgumentException("este emprestimo nao pertence a este membro.");
        }
        if (!membro.temEmprestimo(emprestimo)) {
            throw new IllegalArgumentException("esse livro nao esta pendente para devolucao ou nao existe.");
        }
    }

    void receberLivro(Membro membro, Emprestimo emprestimo) {
        checarDevolucao(membro, emprestimo);

        emprestimo.getLivro().devolverCopia();
        emprestimo.finalizar();
        membro.removerEmprestimo(emprestimo);
        emprestimos.remove(emprestimo);
        System.out.println("livro " + emprestimo.getLivro().getTitulo() + " foi devolvido");
    }

    void pagarMulta(Membro membro, Emprestimo emprestimo) {
        if (!membro.temEmprestimo(emprestimo)) {
            throw new IllegalArgumentException("emprestimo nao existe.");
        }
        if (emprestimo.getMulta(dataAtual) <= 0) {
            throw new IllegalArgumentException("este emprestimo nao esta atrasado.");
        }
        totalMultas += emprestimo.getMulta(dataAtual);
        System.out.print("multa foi paga e ");
        receberLivro(membro, emprestimo);
    }

    LocalDate getDataAtual() { return dataAtual; }
}
