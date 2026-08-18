import java.util.ArrayList;
import java.util.List;

public class Elevator {
    enum Direction {
        UP,
        IDLE,
        DOWN,
    }
    char name;
    int position;
    Direction direction;
    List<Integer> destinations = new ArrayList<>();

    Elevator(char name) {
        this.name = name;
    }

    char getName() {
        return this.name;
    }
}

