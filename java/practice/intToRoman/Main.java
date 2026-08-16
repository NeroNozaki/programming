public class Main {
    public static void main(String[] args) {
        String result = intToRoman(2026);
        System.out.println(result);
    }

     static String intToRoman(int num) {
        if (num < 1 || num > 3999) {
            return "Number out of range.\nNeeds to be a number between 1 and 3999.";
        }

        String total = "";
        String[] symbols = {
            "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"
        };
        int[] values = {
            1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1
        };

        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                num -= values[i];
                total += symbols[i];
            }
        }
        return total;
    }
}
