import java.util.ArrayList;
import java.util.List;

public class Elevator {
    enum Direction {
        UP,
        IDLE,
        DOWN,
    }
    String name;
    int position;
    Direction direction;
    List<Integer> destinations = new ArrayList<>();
}

