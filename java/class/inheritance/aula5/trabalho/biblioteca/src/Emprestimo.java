package biblioteca;

public class Emprestimo {
    static private int global_id = 1;
    private int emprestimoData;
    private int devData;
    private final int id;
    private Livro livro;
    private Membro membro;
    private boolean ativo = true;
    private double taxaDev = 5.0;

    Emprestimo(Membro membro, Livro livro, int emprestimoData) {
        if (membro.getEmprestimos().size() >= membro.getCategoria().getLivrosMax() || livro.getQuantidadeDisponivel() <= 0) {
            throw new IllegalArgumentException("emprestimo nao pode ser feito");
        }

        this.membro = membro;
        this.livro = livro;
        this.emprestimoData = emprestimoData;
        this.devData = emprestimoData + membro.getCategoria().getPrazoMax();
        this.id = global_id++;
    }

    void renovarEmprestimo(Membro membro) {
        if (!membro.getCategoria().getPodeRenovar()) {
            System.err.println("nao pode renovar");
            return;
        }
        if (devData >= emprestimoData) {
            devData += 7;
        }
    }

    void renovarEmprestimo(Membro membro, String cupom) {
        renovarEmprestimo(membro);

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

        this.taxaDev = this.taxaDev * desconto * membro.getCategoria().getDesconto();
    }
}
