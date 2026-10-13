package biblioteca;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprestimo {
    static private int global_id = 1;
    private LocalDate emprestimoData;
    private LocalDate devData;
    private final int id;
    private Livro livro;
    private Membro membro;
    private boolean ativo = true;
    private final double taxaRenov = 5.0;

    Emprestimo(Membro membro, Livro livro, LocalDate emprestimoData) {
        if (membro.getEmprestimos().size() >= membro.getCategoria().getLivrosMax() || livro.getQuantidadeDisponivel() <= 0) {
            throw new IllegalArgumentException("emprestimo nao pode ser feito");
        }

        this.membro = membro;
        this.livro = livro;
        this.emprestimoData = emprestimoData;
        this.devData = emprestimoData.plusDays(membro.getCategoria().getPrazoMax());
        this.id = global_id++;
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
        if (podeRenovar(dataAtual)) { devData = devData.plusDays(7); }
        else System.err.println("renovacao invalida.");
    }

    double calcularTaxaRenovacao() { return this.taxaRenov; }
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
        return this.taxaRenov * desconto;
    }

    void finalizar() { this.ativo = false; }

    double getMulta(LocalDate dataAtual) {
        long diasAtraso = Math.max(0, ChronoUnit.DAYS.between(devData, dataAtual));
        return membro.getCategoria().calcularMulta(diasAtraso);
    }

    int getId() { return id; }
    Livro getLivro() { return livro; }
    boolean isAtivo() { return ativo; }
    Membro getMembro() { return membro; }
}
