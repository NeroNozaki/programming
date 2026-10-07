public class Main {
    public static void main(String[] args) {
        Duck duck = new Duck();
        Cow cow = new Cow();
        Cat cat = new Cat();
        Chicken chicken = new Chicken();

        duck.makeSound();
        cow.makeSound();
        cat.makeSound();
        chicken.makeSound();
    }
}

class Animal {
    public void makeSound() {
        System.out.println("making a sound.");
    }

    public void makeSound(String sound) {
        System.out.println(sound);
    }
}

class Duck extends Animal {
    public void makeSound() {
        System.out.println("quack!");
    }
}


class Cow extends Animal {
    public void makeSound() {
        System.out.println("moooo!");
    }
}

class Cat extends Animal {
    public void makeSound() {
        System.out.println("meow~");
    }
}

class Chicken extends Animal {
    
}
