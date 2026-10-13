package biblioteca;
import java.util.ArrayList;
import java.util.HashMap;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;

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

    void avancarDia() {
        dataAtual = dataAtual.plusDays(1);
        for (Emprestimo emprestimo : emprestimos) {
            emprestimo.atualizarAtraso(dataAtual);
        }
    }
    void avancarDias(int dias) {
        for (int i = 0; i < dias; i++) { avancarDia(); }
    }

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
        if (!membros.contains(membro)) {
            throw new IllegalArgumentException("este membro nao esta registrado.");
        }
        if (!livros.contains(livro)) {
            throw new IllegalArgumentException("nao possuimos este livro.");
        }
        Emprestimo emprestimo = new Emprestimo(membro, livro, dataAtual);
        livro.emprestarCopia();
        emprestimos.add(emprestimo);
        membro.adicionarEmprestimo(emprestimo);
    }

    private void checarDevolucao(Membro membro, Emprestimo emprestimo) {
        if (membro.temMulta()) {
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

    void devolverLivro(Membro membro, Emprestimo emprestimo) {
        checarDevolucao(membro, emprestimo);
        receberLivro(membro, emprestimo);
    }
    void receberLivro(Membro membro, Emprestimo emprestimo) {
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
        if (emprestimo.getMulta() <= 0) {
            throw new IllegalArgumentException("este emprestimo nao esta atrasado.");
        }
        totalMultas += emprestimo.getMulta();
        System.out.print("multa foi paga e ");
        receberLivro(membro, emprestimo);
    }
    void renovarEmprestimo(Emprestimo emprestimo){
        if (!emprestimos.contains(emprestimo)) {
            throw new IllegalArgumentException("emprestimo nao existe");
        }
        System.out.println("taxa de renovacao: " + emprestimo.calcularTaxaRenovacao());
        emprestimo.renovar(dataAtual);
    }
    void renovarEmprestimo(Emprestimo emprestimo, String cupom) {
        if (!emprestimos.contains(emprestimo)) {
            throw new IllegalArgumentException("emprestimo nao existe");
        }
        double taxaRenov = emprestimo.calcularTaxaRenovacao(cupom);
        System.out.println("taxa de renovacao: " + taxaRenov);
        emprestimo.renovar(dataAtual);
    }

    LocalDate getDataAtual() { return dataAtual; }
    HashMap<String, Integer> getNumMembros() {
        HashMap<String, Integer> membrosPorCategoria = new HashMap<>();
        int padrao = 0;
        int premium = 0;
        int vip = 0;
        for (Membro membro : membros) {
            switch(membro.getCategoria()) {
                case Padrao p:
                    padrao++;
                break;
                case Premium p:
                    premium++;
                break;
                case VIP v:
                    vip++;
                break;
                default:
                    throw new IllegalStateException("categoria desconhecida.");
            }
        }

        membrosPorCategoria.put("padrao", padrao);
        membrosPorCategoria.put("premium", premium);
        membrosPorCategoria.put("vip", vip);

        return membrosPorCategoria;
    }
    int getNumEmprestimosAtivos() {
        int emprestimosAtivos = 0;
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.isAtivo()) { emprestimosAtivos++; }
        }
        return emprestimosAtivos;
    }
    int getNumEmprestmosAtrasados() {
        int emprestimosAtrasados = 0;
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getMulta() > 0) { emprestimosAtrasados++; }
        }
        return emprestimosAtrasados;
    }
    double getTotalMultas() { return totalMultas; }
}
