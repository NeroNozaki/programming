import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Request request = new Request();
    }
}

class Request {
    private static int number = 0;
    private final int id;
    int date;
    String clientName;
    private Map<String, Integer> list = new HashMap<>();
    private int status;

    public Request() {
        id = number;
        number++;
    }

    public void addItem(String name, int qty) {
        list.put(name, qty);
    }

    public void removeItem(String name) {
        if (!list.containsKey(name)) {
            System.out.println("List doesn't have that item.");
        } else {
            list.remove(name);
        }
    }

    public void readList() {
        String sList = "";
        for (Map.Entry<String, Integer> entry : list.entrySet()) {
            sList += entry.getKey() + ", " + entry.getValue() + ".\n";
        }
        System.out.println(sList);
    }
}
