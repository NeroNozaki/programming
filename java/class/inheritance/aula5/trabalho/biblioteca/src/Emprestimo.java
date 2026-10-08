package biblioteca;

public class Emprestimo {
    static private int global_id = 1;
    private int emprestimoData;
    private int devData;
    private int id;
    private Livro livro;
    private Membro membro;
    private boolean ativo = true;
    private double taxaDev = 5.0;

    Emprestimo(Membro membro, Livro livro, int emprestimoData) {
        if (membro.getEmprestimo().length >= membro.getCategoria().getLivrosMax() || livro.quantidade_disponivel <= 0) {
            System.err.println("emprestimo nao pode ser feito");
            return;
        }

        this.membro = membro;
        this.livro = livro;
        this.emprestimoData = emprestimoData;
        this.devData = emprestimoData + membro.getCategoria().getPrazoMax();
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

        double discount;

        switch (cupom) {
            case "LEITOR10":
                discount = 0.9;
                break;
            case "RENOVACAO20":
                discount = 0.8;
                break;
            default:
                break;
        }

        this.taxaDev = this.taxaDev * discount * membro.getCategoria().getDesconto();
    }
}
