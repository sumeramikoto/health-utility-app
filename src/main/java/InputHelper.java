import java.util.Scanner;

public class InputHelper {
    private static final Scanner scanner = new Scanner(System.in);

    public static String readLine() {
        return scanner.nextLine();
    }

    public static int readInt() {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }

    public static double readDouble() {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }

    public static String getGenderInput() {
        String gender = InputHelper.readLine().toLowerCase();
        while (!gender.equals("male") && !gender.equals("female")) {
            System.out.print("Please enter either 'Male' or 'Female': ");
            gender = InputHelper.readLine().toLowerCase();
        }
        return gender;
    }

    public static int getInt1or2() {
        int choice = InputHelper.readInt();
        while (choice < 1 || choice > 2) {
            System.out.print("Please enter either 1 or 2: ");
            choice = InputHelper.readInt();
        }
        return choice;
    }

    public static int getActivityLevelInput() {
        int activityLevel = InputHelper.readInt();
        while (activityLevel < 1 || activityLevel > ActivityLevel.values().length) {
            System.out.print("Please enter a number between 1 and " + ActivityLevel.values().length + ": ");
            activityLevel = InputHelper.readInt();
        }
        return activityLevel;
    }
}
