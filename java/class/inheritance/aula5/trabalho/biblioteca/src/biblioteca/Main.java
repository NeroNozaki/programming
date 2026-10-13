package biblioteca;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Biblioteca b = new Biblioteca(LocalDate.of(2026, 10, 10));
        String cupom1 = "LEITOR10";
        String cupom2 = "RENOVACAO20";

        // =================== Fase 1 =======================
        System.out.println("=================== Fase 1 =======================");
        b.printMembros();
        System.out.println("Emprestimos ativos: " + b.getNumEmprestimosAtivos());
        System.out.println("Emprestimos atrasados: " + b.getNumEmprestmosAtrasados());
        System.out.println("Valor total de multas: R$ " + b.getTotalMultas());

        // =================== Fase 2 =======================
        System.out.println("=================== Fase 2 =======================");
        Membro joao = b.registrarMembro("Joao", "joao@mail.com", "padrao");
        Membro maria = b.registrarMembro("Maria", "maria@mail.com", "premium");
        Membro pedro = b.registrarMembro("Pedro", "pedro@mail.com", "vip");
        Membro ana = b.registrarMembro("Ana", "ana@mail.com", "premium");

        Livro o_cortico = b.registrarLivro("O Cortico", "Aluisio Azevedo", 3);
        Livro grande_sertao_veredas = b.registrarLivro("Grande Sertao: Veredas", "Guimaraes Rosa", 2);
        Livro capitaes_da_areia = b.registrarLivro("Capitaes da Areia", "Jorge Amado", 2);
        Livro dom_casmurro = b.registrarLivro("Dom Casmurro", "Machado de Assis", 4);
        Livro vidas_secas = b.registrarLivro("Vidas Secas", "Graciliano Ramos", 1);


        // =================== Fase 3 =======================
        b.emprestarLivro(joao, o_cortico);
        b.emprestarLivro(maria, grande_sertao_veredas);
        b.emprestarLivro(maria, capitaes_da_areia);
        b.emprestarLivro(pedro, dom_casmurro);
        b.emprestarLivro(ana, vidas_secas);


        // =================== Fase 4 =======================
        System.out.println("=================== Fase 4 =======================");
        maria.getEmprestimo(capitaes_da_areia).calcularTaxaRenovacao();
        maria.getEmprestimo(capitaes_da_areia).calcularTaxaRenovacao(cupom1);
        maria.getEmprestimo(capitaes_da_areia).calcularTaxaRenovacao(cupom2);
        b.renovarEmprestimo(maria.getEmprestimo(capitaes_da_areia), cupom1);

        try {
            b.renovarEmprestimo(joao.getEmprestimo(o_cortico));
            System.out.println("deveria ter lançado uma exceção.");
        } catch (IllegalStateException e) {
            System.out.println("empréstimo inválido foi rejeitado.");
        }

        // =================== Fase 5 =======================
        System.out.println("=================== Fase 5 =======================");
        // 2026-10-25
        b.avancarDias(15);

        joao.getEmprestimo(o_cortico).exibir();
        System.out.println("multa: R$ " + joao.getEmprestimo(o_cortico).getMulta());
        System.out.println("multa com 15% de desconto: R$ " + joao.getEmprestimo(o_cortico).getMulta(15));

        // 2026-11-01
        b.avancarDias(7);

        joao.getEmprestimo(o_cortico).exibir();
        System.out.println("multa: R$ " + joao.getEmprestimo(o_cortico).getMulta());

        ana.getEmprestimo(vidas_secas).exibir();
        System.out.println("multa: R$ " + ana.getEmprestimo(vidas_secas).getMulta());

        b.devolverLivro(pedro.getEmprestimo(dom_casmurro));


        // =================== Fase 6 =======================
        System.out.println("=================== Fase 6 =======================");
        b.pagarMulta(joao.getEmprestimo(o_cortico));
        b.pagarMulta(ana.getEmprestimo(vidas_secas));

        try {
            b.devolverLivro(maria.getEmprestimo(grande_sertao_veredas));
            System.out.println("devolucao aceita.");
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            System.out.println("devolucao rejeitada.");
        }
        System.out.println();

        // =================== Fase 7 =======================
        System.out.println("=================== Fase 7 =======================");
        System.out.println("Dados de Maria: ");
        maria.printEmprestimos();
        System.out.println("Multa total: R$" + maria.getMultaTotal());
        maria.printHistEmprestimos();

        System.out.println("\nDados de Joao: ");
        joao.printEmprestimos();
        System.out.println("Multa total: R$" + joao.getMultaTotal());
        joao.printHistEmprestimos();

        // =================== Fase 8 =======================
        System.out.println("=================== Fase 8 =======================");
        joao.setCategoria("premium");
        b.emprestarLivro(joao, o_cortico);


        // =================== Fase 9 =======================
        System.out.println("=================== Fase 9 =======================");
        Livro teste1 = b.registrarLivro("teste1", "Nero", 4);
        Livro teste2 = b.registrarLivro("teste2", "Nero", 1);

        b.emprestarLivro(joao, teste1);
        b.emprestarLivro(joao, teste1);
        b.emprestarLivro(joao, teste1);
        b.emprestarLivro(joao, teste2);

        try {
            b.emprestarLivro(joao, teste1);
            System.out.println("emprestimo aceito.");
        } catch (Exception e) {
            System.out.println();
            System.out.println(e);
            System.out.println("emprestimo rejeitado.");
        }

        try {
            b.emprestarLivro(maria, teste2);
            System.out.println("emprestimo aceito.");
        } catch (Exception e) {
            System.out.println();
            System.out.println(e);
            System.out.println("emprestimo rejeitado.");
        }

        // =================== Fase 10 =======================
        System.out.println("=================== Fase 10 =======================");
        System.out.println();
        System.out.println("Olhe o codigo ^_^");
        System.out.println();

        // joao.getEmprestimo(o_cortico).id = 69;
        // nao funciona pq id e private

        // joao.email = "emailmuitofodawow@mail.com";
        // nao funciona pq email e private

        // class novaClasse extends Padrao {};
        // nao funciona pq Padrao e final

        // joao.getCategoria().valorMulta = 420;
        //nao funciona pq valorMulta e private e final


        // =================== Fase 11 =======================
        System.out.println("=================== Fase 11 =======================");
        b.printMembros();
        System.out.println("Emprestimos ativos: " + b.getNumEmprestimosAtivos());
        System.out.println("Emprestimos atrasados: " + b.getNumEmprestmosAtrasados());
        System.out.println("Valor total de multas: R$ " + b.getTotalMultas());
    }
}
