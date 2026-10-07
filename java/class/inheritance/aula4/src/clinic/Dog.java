package clinic;

class Dog extends Animal {
    Size size;
    enum Size {
        S,
        M,
        L,
    }

    Dog(String name, String tutor_name, int age, double weight, int size) {
        super(name, tutor_name, age, weight);
        switch(size) {
            case 1:
            this.size = Size.S;
            break;
            case 2:
            this.size = Size.M;
            break;
            case 3:
            this.size = Size.L;
            break;
        }
    }
    void printCard() {
        super.printCard();
        System.out.println("size: " + size);
    }
}
