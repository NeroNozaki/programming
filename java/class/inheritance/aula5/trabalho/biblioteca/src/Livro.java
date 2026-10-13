package biblioteca;

class Livro {
    private final String titulo;
    private final String autor;
    private final int quantidadeTotal;
    private int quantidadeDisponivel;

    Livro(String titulo, String autor, int quantidadeTotal) {
        this.titulo = titulo;
        this.autor = autor;
        this.quantidadeTotal = quantidadeTotal;
        this.quantidadeDisponivel = this.quantidadeTotal;
    }

    void emprestarCopia() {
        if (quantidadeDisponivel > 0) {
            quantidadeDisponivel--;
        }
        else {
            quantidadeDisponivel = 0;
            throw new IllegalStateException("livro fora de estoque.");
        }
    }

    void devolverCopia() {
        if (quantidadeDisponivel < quantidadeTotal) {
            quantidadeDisponivel++;
        }
        else {
            quantidadeDisponivel = quantidadeTotal;
            throw new IllegalStateException("estoque cheio.");
        }
    }

    int getQuantidadeDisponivel() {
        return this.quantidadeDisponivel;
    }
}
