package library;

public class Emprestimo {
    static private int global_id = 1;
    private int data_de_emprestimo;
    private int data_de_dev;
    private int id;
    private Livro livro;
    private Membro membro;
    private boolean ativo = true;
    private double taxaDev = 5.0;

    Emprestimo(Membro membro, Livro livro, int data_de_emprestimo) {
        if (membro.getLivros() >= membro.categoria.getMaxLivros() || livro.quantidade_disponivel <= 0) {
            System.err.println("emprestimo nao pode ser feito");
            return;
        }

        this.membro = membro;
        this.livro = livro;
        this.data_de_emprestimo = data_de_emprestimo;
        this.data_de_dev = data_de_emprestimo + membro.categoria.prazo_maximo;
    }

    void renovarEmprestimo(Membro membro) {
        if (!membro.categoria.pode_renovar) {
            System.err.println("nao pode renovar");
            return;
        }
        if (data_de_dev >= data_de_emprestimo) {
            data_de_dev += 7;
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

        this.taxaDev = this.taxaDev * discount * membro.categoria.discount;
    }
}
