package biblioteca;
import java.util.ArrayList;
import java.util.HashMap;
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

    Membro registrarMembro(String nome, String email, String categoria) {
        Membro m = new Membro(nome, email, dataAtual, categoria);
        membros.add(m);
        return m;
    }
    void registrarMembro(Membro membro) {
        membros.add(membro);
    }
    Livro registrarLivro(String titulo, String autor, int quantidadeTotal) {
        Livro l = new Livro(titulo, autor, quantidadeTotal);
        livros.add(l);
        return l;
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

    void devolverLivro(Emprestimo emprestimo) {
        checarDevolucao(emprestimo);
        receberLivro(emprestimo);
    }

    void pagarMulta(Emprestimo emprestimo) {
        checarMulta(emprestimo);

        totalMultas += emprestimo.getMulta();
        System.out.print("Multa de " + emprestimo.getMulta() + " foi paga e ");
        receberLivro(emprestimo);
    }

    void renovarEmprestimo(Emprestimo emprestimo){
        if (!emprestimos.contains(emprestimo)) {
            throw new IllegalArgumentException("emprestimo nao existe");
        }

        emprestimo.calcularTaxaRenovacao();
        emprestimo.renovar(dataAtual);
    }

    void renovarEmprestimo(Emprestimo emprestimo, String cupom) {
        if (!emprestimos.contains(emprestimo)) {
            throw new IllegalArgumentException("emprestimo nao existe");
        }
        emprestimo.calcularTaxaRenovacao(cupom);
        emprestimo.renovar(dataAtual);
    }

    LocalDate getDataAtual() { return dataAtual; }
    private HashMap<String, Integer> getNumMembros() {
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

    void printMembros() {
        HashMap<String, Integer> membros = getNumMembros();
        System.out.print("Padrao: " + membros.get("padrao"));
        System.out.print(", Premium: " + membros.get("premium"));
        System.out.print(", VIP: " + membros.get("vip"));
        System.out.println();
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
            if (emprestimo.getDiasAtraso() > 0) { emprestimosAtrasados++; }
        }
        return emprestimosAtrasados;
    }

    double getTotalMultas() { return totalMultas; }

    private void receberLivro(Emprestimo emprestimo) {
        Membro membro = emprestimo.getMembro();

        emprestimo.getLivro().devolverCopia();
        emprestimo.finalizar();
        membro.removerEmprestimo(emprestimo);
        emprestimos.remove(emprestimo);
        System.out.println("Livro " + emprestimo.getLivro().getTitulo() + " foi devolvido!");
    }

    private void checarDevolucao(Emprestimo emprestimo) {
        Membro membro = emprestimo.getMembro();

        checarMembro(membro);
        if (membro.temMulta()) {
            throw new IllegalArgumentException("nao pode devolver nenhum livro enquanto voce tem um emprestimo atrasado.");
        }
        if (!emprestimo.isAtivo()) {
            throw new IllegalArgumentException("este emprestimo nao esta ativo.");
        }
    }

    private void checarMulta(Emprestimo emprestimo) {
        Membro membro = emprestimo.getMembro();

        checarMembro(membro);
        if (!emprestimos.contains(emprestimo)) {
            throw new IllegalArgumentException("emprestimo nao existe.");
        }
        if (!membro.temEmprestimo(emprestimo)) {
            throw new IllegalArgumentException("emprestimo nao existe.");
        }
        if (!emprestimo.isAtivo()) {
            throw new IllegalArgumentException("emprestimo ja foi encerrado.");
        }
        if (emprestimo.getMulta() <= 0) {
            throw new IllegalArgumentException("este emprestimo nao esta atrasado.");
        }
    }

    private void checarMembro(Membro membro) {
        if(!membros.contains(membro)) {
            throw new IllegalArgumentException("este membro nao esta registrado.");
        }
    }
}
