package aula6;

public class SalaDeAula {
    String codigo;
    int capacidade;
    int tamanho;
    Aluno[] alunos;

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
}
