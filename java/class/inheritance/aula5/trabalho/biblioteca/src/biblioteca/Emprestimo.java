package biblioteca;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprestimo {
    static private int global_id = 1;
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

    boolean podeRenovar(LocalDate dataAtual) {
        if (!ativo){
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
    void renovar(LocalDate dataAtual) {
        if (podeRenovar(dataAtual)) {
            devData = devData.plusDays(7);
            System.out.println("nova data de devolucao: " + devData);
        }
        else System.err.println("renovacao invalida.");
    }

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

        System.out.println("taxa de renovacao com cupom " + cupom + ": R$ " + calcularTaxaRenovacao(cupom));
        return this.taxaRenov * desconto;
    }

    void finalizar() { this.ativo = false; }

    void atualizarAtraso(LocalDate dataAtual){
        this.diasAtraso = Math.max(0, ChronoUnit.DAYS.between(devData, dataAtual));
    }
    double getMulta() {
        return membro.getCategoria().calcularMulta(diasAtraso);
    }

    int getId() { return id; }
    Livro getLivro() { return livro; }
    boolean isAtivo() { return ativo; }
    Membro getMembro() { return membro; }
    long getDiasAtraso() {
        return this.diasAtraso;
    }

    void exibir() {
        System.out.println("ID: " + id);
        System.out.println("Livro: " + livro.getTitulo() + " de " + livro.getAutor());
        System.out.println("Membro: " + membro.getNome());
        System.out.println("Data de emprestimo: " + emprestimoData);
        System.out.println("Data de devolucao: " + devData);
        System.out.println("Dias de atraso: " + diasAtraso);
        System.out.println("Multa atual: " + getMulta());
    }
}
