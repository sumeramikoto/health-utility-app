public class OutputHelper {
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
        System.out.println("6. Set Weight Goal");
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

    public static void displayBMIResult(double bmi, String bmiCategory) {
        System.out.println("=== BMI Calculation ===");
        System.out.printf("Your BMI is %.2f\n", bmi);
        System.out.println("Category: " + bmiCategory);
    }

    public static void displayBMRResult(double bmr) {
        System.out.println("=== BMR Calculation ===");
        System.out.printf("Your Basal Metabolic Rate (BMR) is %.2f\n", bmr);
        System.out.println("This is the number of calories your body needs to maintain basic functions at rest.");
    }

    public static void displayTDEEResult(double tdee) {
        System.out.println("=== TDEE Calculation ===");
        System.out.printf("Your Total Daily Energy Expenditure (TDEE) is %.2f\n", tdee);
        System.out.println("This is the estimated number of calories you burn per day based on your activity level.");
    }

    public static void displayBodyFatPercentageResult(double bodyFatPercentage, String bodyFatCategory) {
        System.out.println("=== Body Fat Percentage Calculation ===");
        System.out.printf("Your estimated body fat percentage is %.2f%%\n", bodyFatPercentage);
        System.out.println("Category: " + bodyFatCategory);
    }

    public static void displayWeightGoalMenu() {
        System.out.println("=== Weekly Weight Goal ===");
        System.out.println("1. Gain Weight");
        System.out.println("2. Lose Weight");
        System.out.print("Enter your desired goal: ");
    }

    public static void displayWeightGoalPaceMenu() {
        System.out.println("*** Preferred Pace ***");
        System.out.println("1. Steady (0.5 kg/week)");
        System.out.println("2. Accelerated (1 kg/week)");
        System.out.print("Enter your desired pace: ");
    }

    public static void displayWeightGoalResult(String goalType, double rate, double targetCalories) {
        System.out.println("Your weekly weight goal has been set!");
        System.out.println("Your goal is to " + goalType + " weight by " + rate + " kg/week");
        System.out.printf("Target calories: %.2f calories/day\n", targetCalories);
    }
}
