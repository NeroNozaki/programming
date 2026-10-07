package clinic;

class Cat extends Animal {
    boolean castrated;

    Cat(String name, String tutor_name, int age, double weight, boolean castrated) {
        super(name, tutor_name, age, weight);
        this.castrated = castrated;
        discount = this.castrated ? 0.9 : discount;
    }
    void printCard() {
        super.printCard();
        System.out.println("castrated: " + (castrated ? "yes" : "no"));
    }
}

