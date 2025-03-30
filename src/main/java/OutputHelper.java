public class OutputHelper {
    public static void displayMenu(String title, String[] options) {
        System.out.println("\n===== " + title + " =====");
        for (String option : options) {
            System.out.println(option);
        }
    }

    public static void displayInfo(String title, String[] content) {
        System.out.println("\n===== " + title + " =====");
        for (String line : content) {
            System.out.println(line);
        }
    }
}
