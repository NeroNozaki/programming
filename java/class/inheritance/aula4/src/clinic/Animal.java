package clinic;

abstract public class Animal {
    String name;
    String tutor_name;
    int age;
    double weight;
    double price = 120.00;
    double discount = 1;


    Animal(String name, String tutor_name, int age, double weight) {
        this.name = name;
        this.tutor_name = tutor_name;
        this.age = age;
        this.weight = weight;
    }
    void printCard() {
        System.out.println("name: " + name);
        System.out.println("tutor_name: " + tutor_name);
        System.out.println("age: " + age);
        System.out.println("weight: " + weight);
        System.out.println("price: " + (price * discount));
    }

}

