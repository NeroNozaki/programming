public class Command {
    enum Type {
        STEP,
        REQUEST,
    }
    Type type;
    String request;
}

