public class Main {
    public static void main(String[] args) {
        Book book = new Book();
        int ano = 2000;
        book.setYear(ano);
        System.out.println(book.getYear());

        ano = 3000;
        System.out.println(book.getYear());
    }
}
