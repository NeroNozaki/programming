public class Main {
    public static void main(String[] args) {
        System.out.println(dateVerifier());
    }

    static String dateVerifier() {
        String date = "09/08/2026";
        String sDay = "";
        String sMonth = "";
        String sYear = "";

        for (int i = 0; i < 2; i++) {
            sDay += date.charAt(i);
        }
        for (int i = 3; i < 5; i++) {
            sMonth += date.charAt(i);
        }
        for (int i = 6; i < date.length(); i++) {
            sYear += date.charAt(i);
        }
        int day = Integer.parseInt(sDay);
        int month = Integer.parseInt(sMonth);
        int year = Integer.parseInt(sYear);

        if (year < 0) {
            return "invalid date";
        }

        boolean leap = false; 

        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) {
                    leap = true;
                } else {
                    leap = false;
                }
            } else {
                leap = true;
            }
        } else {
            leap = false;
        }

        if (month > 12 || month < 1) {
            return "invalid date";
        }

        int max = 0;

        if (month == 2) {
            if (leap) max = 29; else max = 28;
        }
        else if (
                 month == 4 ||
                 month == 6 ||
                 month == 9 ||
                 month == 11
                 )
            {
                max = 30;
            } else {
            max = 31;
        }

        if (day <= max && day >= 1) {
            return "valid date";
        } else {
            return "invalid date";
        }

    }
}
