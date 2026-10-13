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
    private double taxaRenov = 5.0;

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

    int renovarEmprestimo() {
        if (!this.membro.getCategoria().getPodeRenovar()) {
            System.err.println("nao pode renovar");
            return 1;
        }
        if (!dataAtual.isAfter(devData)) {
            devData = devData.plusDays(7);
        }
        return 0;
    }

    void renovarEmprestimo(String cupom) {
        int result = renovarEmprestimo();
        if (result == 1) return;

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

        this.taxaRenov = this.taxaRenov * desconto;
    }

    void finalizarEmpresitmo() {
        this.ativo = false;
        membro.adicionarHistEmprestimos(this);
    }

    int id() { return id; }
    Livro livro() { return livro; }
}
