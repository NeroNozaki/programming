import java.util.List;
import java.util.ArrayList;

class ElevatorSystem {
    List<Elevator> elevators;
    List<Floor> floors;

    ElevatorSystem(int elevatorNum, int floorNum) {
        elevators = new ArrayList<>();
        floors = new ArrayList<>();
        for (int i = 0; i < elevatorNum; i++) {
            elevators.add(new Elevator((char)('A'+1)));
            System.out.println("Elevator " + elevators.get(i).getName() + " created.");
        }
        for (int i = 0; i < floorNum; i++) {
            floors.add(new Floor(i+1));
            System.out.println("Floor number " + floors.get(i).getNumber() + " created.");
        }
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
