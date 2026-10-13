package biblioteca;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Main {
    public static void main(String[] args) {
        Biblioteca b = new Biblioteca(LocalDate.of(2026, 10, 10));
        String cupom1 = "LEITOR10";
        String cupom2 = "RENOVACAO20";

        Membro joao = b.registrarMembro("Joao", "joao@mail.com", "padrao");
        Membro maria = b.registrarMembro("Maria", "maria@mail.com", "premium");
        Membro pedro = b.registrarMembro("Pedro", "pedro@mail.com", "vip");
        Membro ana = b.registrarMembro("Ana", "ana@mail.com", "premium");

        Livro o_cortico = b.registrarLivro("O Cortico", "Aluisio Azevedo", 3);
        Livro grande_sertao_veredas = b.registrarLivro("Grande Sertao: Veredas", "Guimaraes Rosa", 2);
        Livro capitaes_da_areia = b.registrarLivro("Capitaes da Areia", "Jorge Amado", 2);
        Livro dom_casmurro = b.registrarLivro("Dom Casmurro", "Machado de Assis", 4);
        Livro vidas_secas = b.registrarLivro("Vidas Secas", "Graciliano Ramos", 1);

        b.emprestarLivro(joao, o_cortico);
        b.emprestarLivro(maria, grande_sertao_veredas);
        b.emprestarLivro(maria, capitaes_da_areia);
        b.emprestarLivro(pedro, dom_casmurro);
        b.emprestarLivro(ana, vidas_secas);

        maria.getEmprestimo(capitaes_da_areia).calcularTaxaRenovacao();
        maria.getEmprestimo(capitaes_da_areia).calcularTaxaRenovacao(cupom1);
        maria.getEmprestimo(capitaes_da_areia).calcularTaxaRenovacao(cupom2);
        b.renovarEmprestimo(maria.getEmprestimo(capitaes_da_areia), cupom1);
    }
}
