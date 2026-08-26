public class Main {
    public static void main(String[] args) {
        SalaDeAula saladeaula = new SalaDeAula(10);
        saladeaula.criarAluno("maria", 123);
        saladeaula.criarAluno("lucas", 980);
        saladeaula.criarAluno("carlos", 707);
        saladeaula.listarAlunos();
    }
}

