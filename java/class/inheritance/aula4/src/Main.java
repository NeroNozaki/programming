package clinic;

public class Main {
    public static void main(String[] args) {
        Dog thor = new Dog("Thor", "Nicolas", 5, 1000, 3);
        Dog mel = new Dog("Mel", "Nicolas", 7, 20, 1);
        Cat mingau = new Cat("Mingau", "Nicolas", 5, 5, true);
        thor.printCard();
        System.out.println();
        mel.printCard();
        System.out.println();
        mingau.printCard();
        System.out.println();
    }
}
