package aula6;

public class Main {
    public static void main(String[] args) {
        Car[] array = new Car[10];
        for (int i = 0; i < array.length; i++) {
            if (i != 5) {
                array[i] = new Car();
            }
            System.out.println(array[i]);
        }
    }
}

class Car {
    int num;
}
