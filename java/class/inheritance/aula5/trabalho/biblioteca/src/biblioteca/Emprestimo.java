package biblioteca;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprestimo {
    private static int global_id = 1;

    private LocalDate emprestimoData;
    private long diasAtraso;
    private LocalDate devData;
    private final int id;
    private Livro livro;
    private Membro membro;
    private boolean ativo = true;
    private final double taxaRenov = 5.0;

    Emprestimo(Membro membro, Livro livro, LocalDate emprestimoData) {
        if (!membro.podeFazerEmprestimo()) {
            throw new IllegalArgumentException("este membro atigingiu seu limite de emprestimos.");
        }
        if (livro.getQuantidadeDisponivel() <= 0) {
            throw new IllegalArgumentException("livro fora de estoque.");
        }

        this.membro = membro;
        this.livro = livro;
        this.emprestimoData = emprestimoData;
        this.devData = emprestimoData.plusDays(membro.getCategoria().getPrazoMax());
        this.id = global_id++;
        System.out.println("\nEmprestimo feito!");
        this.exibir();
    }

    // Operações que alteram o estado do empréstimo

    void renovar(LocalDate dataAtual) {
        System.out.println();
        if (podeRenovar(dataAtual)) {
            devData = devData.plusDays(7);
            System.out.println("Renovacao feita!");
            System.out.println("Nova data de devolucao: " + devData);
        } else {
            throw new IllegalStateException();
        }
    }

    void finalizar() {
        this.ativo = false;
    }

    void atualizarAtraso(LocalDate dataAtual) {
        this.diasAtraso = Math.max(0, ChronoUnit.DAYS.between(devData, dataAtual));
    }

    // Cálculos de taxas e multas

    double calcularTaxaRenovacao() {
        System.out.println("taxa de renovacao: R$ " + this.taxaRenov);
        return this.taxaRenov;
    }

    double calcularTaxaRenovacao(String cupom) {
        double desconto;

        switch (cupom) {
            case "LEITOR10":
                desconto = 0.9;
                break;
            case "RENOVACAO20":
                desconto = 0.8;
                break;
            default:
                desconto = 1;
                break;
        }

        double taxa = this.taxaRenov * desconto;
        System.out.println("taxa de renovacao com cupom " + cupom + ": R$ " + taxa);
        return taxa;
    }

    double getMulta() {
        return membro.getCategoria().calcularMulta(diasAtraso);
    }

    double getMulta(int desconto) {
        return membro.getCategoria().calcularMulta(diasAtraso, desconto);
    }

    // Consultas dos dados do empréstimo

    int getId() {
        return id;
    }

    Livro getLivro() {
        return livro;
    }

    boolean isAtivo() {
        return ativo;
    }

    Membro getMembro() {
        return membro;
    }

    long getDiasAtraso() {
        return this.diasAtraso;
    }

    // Exibição

    void exibir() {
        System.out.println();
        System.out.println("ID: " + id);
        System.out.println("Livro: " + livro.getTitulo() + " de " + livro.getAutor());
        System.out.println("Membro: " + membro.getNome());
        System.out.println("Data de emprestimo: " + emprestimoData);
        System.out.println("Data de devolucao: " + devData);
        System.out.println("Dias de atraso: " + diasAtraso);
        System.out.println("Multa atual: R$ " + getMulta());
        System.out.println();
    }

    // Validação auxiliar

    private boolean podeRenovar(LocalDate dataAtual) {
        if (!ativo) {
            System.err.println("nao pode renovar um emprestimo inativo.");
            return false;
        }
        if (!this.membro.getCategoria().getPodeRenovar()) {
            System.err.println("sua categoria nao pode renovar emprestimos.");
            return false;
        }
        if (dataAtual.isAfter(devData)) {
            System.err.println("nao pode renovar um emprestimo atrasado.");
            return false;
        }
        return true;
    }
}
