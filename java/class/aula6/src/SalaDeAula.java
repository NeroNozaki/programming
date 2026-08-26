public class SalaDeAula {
    private String codigo;
    private int capacidade;
    private int tamanho;
    private Aluno[] alunos;

    SalaDeAula(int capacidade) {
        this.alunos = new Aluno[capacidade];
        this.capacidade = capacidade;
        this.tamanho = 0;
    }

    public void criarAluno(String nome, int matricula) {
        if (tamanho == capacidade) {
            System.err.println("Maxima capacidade excedida.");
            return;
        }
        alunos[tamanho] = new Aluno(nome, matricula);
        tamanho++;
    }

    public void listarAlunos() {
        if (this.tamanho <= 0) {
            System.err.println("nao ha alunos nesta sala");
        }
        for (int i = 0; i < this.tamanho; i++) {
            System.out.print(alunos[i].nome + " - " + alunos[i].matricula + "\n");
        }
    }

    public void remover() {
        if (this.tamanho == 0) {
            System.err.println("sala vazia");
            return;
        }
        this.tamanho--;
    }
}
