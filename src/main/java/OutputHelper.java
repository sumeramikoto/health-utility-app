import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

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
        System.out.printf("Your target calories for this week will be %.2f calories/day\n", targetCalories);
    }

    public static void displayCalorieInfo(LocalDate today, double targetCalories, double currentCalories) {
        System.out.println("=== Calorie Intake Tracker ===");
        System.out.println("Date: " + today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        System.out.printf("Target daily calories: %.2f calories\n", targetCalories);
        System.out.printf("Current intake today: %.2f calories\n", currentCalories);
        System.out.printf("Remaining: %.2f calories\n", targetCalories - currentCalories);
    }

    public static void displayFoodLog(Map<String, Double> foodEntries) {
        System.out.println("\n*** Today's Food Log ***");
        for (Map.Entry<String, Double> entry : foodEntries.entrySet()) {
            System.out.println("- " + entry.getKey() + ": " + String.format("%.0f", entry.getValue()) + " calories");
        }
    }

    public static void displayFoodItemOptions() {
        System.out.println("*** Options ***");
        System.out.println("1. Add food item");
        System.out.println("2. Return to main menu");
        System.out.print("Select an option: ");
    }

    public static void displayCalorieIntakeUpdatedInfo(String foodItem, double foodCalories, double newTotal, double targetCalories) {
        System.out.println("*** Calorie Intake Updated ***");
        System.out.printf("Added: %s (%.2f calories)\n", foodItem, foodCalories);
        System.out.printf("New total: %.2f calories\n", newTotal);
        System.out.printf("Remaining: %.2f calories\n", targetCalories - newTotal);
    }

    public static void displayWaterIntakeInfo(LocalDate today, double recommendedIntake, double currentIntake) {
        double progressPercentage = (currentIntake / recommendedIntake) * 100;
        System.out.println("=== Water Intake Tracker ===");
        System.out.println("Date: " + today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        System.out.printf("Recommended daily intake: %.0f ml\n", recommendedIntake);
        System.out.printf("Current intake for today: %.2f ml\n", currentIntake);
        System.out.printf("Progress: %.2f%%\n", progressPercentage);
    }

    public static void displayWaterIntakeOptions() {
        System.out.println("*** Options ***");
        System.out.println("1. Add water intake");
        System.out.println("2. Return to main menu");
        System.out.print("Select an option: ");
    }

    public static void displayWaterIntakeUpdatedInfo(double newCurrentIntake, double recommendedIntake) {
        double progressPercentage = (newCurrentIntake / recommendedIntake) * 100;
        System.out.println("*** Water Intake Updated ***");
        System.out.printf("Current intake for today: %.2f ml\n", newCurrentIntake);
        System.out.printf("Progress: %.2f%%\n", progressPercentage);
    }
}
