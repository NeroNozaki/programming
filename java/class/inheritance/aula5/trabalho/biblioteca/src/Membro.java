package biblioteca;
import java.util.ArrayList;

public class Membro {
    final private String nome;
    final private String email;
    final private int cadastroData;
    private Categoria categoria;
    private final ArrayList<Emprestimo> emprestimos = new ArrayList<>();

    Membro(String nome, String email, int cadastroData, String categoria) {
        setCategoria(categoria);
        this.nome = nome;
        this.email = email;
        this.cadastroData = cadastroData;
    }

    void setCategoria(String categoria) {
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
            throw new IllegalArgumentException("categoria de membro invalida.");
            return;
        }
    }

    Categoria getCategoria() { return categoria; }

    ArrayList<Emprestimo> getEmprestimos() { return emprestimos; }
}
