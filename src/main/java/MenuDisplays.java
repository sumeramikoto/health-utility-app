public class MenuDisplays {
    public static void displayAuthMenu() {
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print("Please select an option: ");
    }

    public static void displayMainMenu(String title) {
        System.out.println(title);
        System.out.println("1. View User Profile");
        System.out.println("2. BMI Calculator");
        System.out.println("3. BMR Calculator");
        System.out.println("4. View TDEE (Total Daily Energy Expenditure)");
        System.out.println("5. Calculate Body Fat Percentage");
        System.out.println("6. Set Weekly Weight Goal");
        System.out.println("7. Track Daily Calorie Intake");
        System.out.println("8. Track Daily Water Intake");
        System.out.println("9. Update User Information");
        System.out.println("10. Sign Out");
        System.out.print("Select an option: ");
    }

    public static void displayActivityLevelMenu() {
        System.out.println("=== Select Activity Level ===");
        System.out.println("1. Sedentary (little or no exercise)");
        System.out.println("2. Lightly active (light exercise/sports 1-3 days/week)");
        System.out.println("3. Moderately active (moderate exercise/sports 3-5 days/week)");
        System.out.println("4. Very active (hard exercise/sports 6-7 days a week)");
        System.out.println("5. Extra active (very hard exercise, physical job or training twice a day)");
        System.out.print("Enter activity level: ");
    }
}
