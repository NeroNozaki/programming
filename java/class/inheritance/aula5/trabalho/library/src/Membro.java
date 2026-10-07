package library;

public class Membro {
    private String nome;
    private String email;
    private int data_de_cadastro;
    Categoria categoria;

    private int livros_emprestados;

    Membro(String nome, String email, int data_de_cadastro, String categoria) {
        setCategoria(categoria);
        this.nome = nome;
        this.email = email;
        this.data_de_cadastro = data_de_cadastro;
    }

    public void setCategoria(String categoria) {
        switch (categoria) {
            case "padrao":
                this.categoria = new Padrao();
                break;

            case "premium":
                this.categoria = new Premium();
                break;
            case "vip":
                this.categoria = new VIP();
                break;
            default:
                System.err.println("categoria de membro invalida.");
                return;
        }
    }

    int getLivros() {
        return livros_emprestados;
    }
}
