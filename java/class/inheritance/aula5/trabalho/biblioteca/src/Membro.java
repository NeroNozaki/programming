package biblioteca;

public class Membro {
   final private String nome;
   final private String email;
   final private int cadastroData;
   final private Categoria categoria;
   private Emprestimo[] emprestimos;

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
           System.err.println("categoria de membro invalida.");
           return;
       }
   }

   Categoria getCategoria() { return categoria; }

   Emprestimo[] getEmprestimo() { return emprestimos; }
}
