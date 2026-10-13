package biblioteca;

import com.sun.nio.sctp.IllegalUnbindException;

class Livro {
    private final String titulo;
    private final String autor;
    private final int quantidadeTotal;
    private int quantidadeDisponivel;

    Livro(String titulo, String autor, int quantidadeTotal) {
        if (quantidadeTotal <= 0) {
            throw new IllegalArgumentException("quantidade do livro nao pode ser menor que 1.");
        }
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
            throw new IllegalStateException("livro fora de estoque.");
        }
    }

    void devolverCopia() {
        if (quantidadeDisponivel < quantidadeTotal) {
            quantidadeDisponivel++;
        }
        else {
            throw new IllegalStateException("estoque cheio.");
        }
    }

    int getQuantidadeDisponivel() {
        return this.quantidadeDisponivel;
    }

    String getTitulo() { return this.titulo; }
    String getAutor() { return this.autor; }
}
