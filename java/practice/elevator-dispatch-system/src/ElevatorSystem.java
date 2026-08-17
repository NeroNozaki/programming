import java.util.List;
import java.util.ArrayList;

class ElevatorSystem {
    List<Elevator> elevators;
    List<Floor> floors;

    ElevatorSystem() {
        elevators = new ArrayList<>();
        floors = new ArrayList<>();
    }

    void begin() {
    }

    void handleInput() {
        String input = "";
        Command command = parseInput(input);

        switch (command.type) {
        case STEP:
            step();
            break;
        case REQUEST:
            makeRequest(command);
            break;

        default:
            break;
        }
    }

    Command parseInput(String input) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    void makeRequest(Command command) {
    }

    void callElevator() {
    }

    void step() {
    }
}
