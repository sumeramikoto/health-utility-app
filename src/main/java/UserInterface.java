import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class UserInterface {
    private UserSession currentSession;
    private final UserManager userManager;
    private final IWeightGoalManager goalManager;
    private final ICalorieTracker calorieTracker;
    private final IWaterTracker waterTracker;

    public UserInterface(UserManager userManager, IWeightGoalManager goalManager, ICalorieTracker calorieTracker, IWaterTracker waterTracker) {
        this.userManager = userManager;
        this.goalManager = goalManager;
        this.calorieTracker = calorieTracker;
        this.waterTracker = waterTracker;
    }

    public void start() {
        System.out.println("Welcome to the app!\n");
        boolean running = true;
        while (running) {
            if (currentSession == null) {
                running = displayAuthMenu();
            } else {
                displayMainMenu();
            }
        }
    }

    private boolean displayAuthMenu() {
        OutputHelper.displayAuthMenu();
        int choice = InputHelper.readInt();
        switch (choice) {
            case 1:
                clearConsole();
                currentSession = userManager.login();
                return true;
            case 2:
                clearConsole();
                currentSession = userManager.register();
                return true;
            case 3:
                System.out.println("Goodbye!");
                return false;
            default:
                System.out.println("Invalid option. Please try again.");
                return true;
        }
    }

    private void displayMainMenu() {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String mainMenuTitle = "Welcome " + currentSession.getUsername() + "!                             Date: " + today;
        OutputHelper.displayMainMenu(mainMenuTitle);
        int choice = InputHelper.readInt();
        switch (choice) {
            case 1 -> viewUserProfile();
            case 2 -> viewBMI();
            case 3 -> viewBMR();
            case 4 -> viewTDEE();
            case 5 -> calculateBodyFatPercentage();
            case 6 -> setWeeklyWeightGoal();
            case 7 -> trackCalorieIntake();
            case 8 -> trackWaterIntake();
            case 9 -> updateUserInformation();
            case 10 -> signOut();
            default -> System.out.println("Invalid option. Please try again.");
        }
    }

    private void signOut() {
        currentSession = null;
        clearConsole();
        System.out.println("Signed out successfully.");
    }

    private void viewUserProfile() {
        clearConsole();
        UserProfile profile = currentSession.getProfile();
        System.out.println("=== " + currentSession.getUsername() + "'s Profile ===");
        System.out.println("Age: " + profile.getAge());
        System.out.println("Height: " + profile.getHeightM() + " m");
        System.out.println("Weight: " + profile.getWeightKG() + " kg");
        System.out.println("Gender: " + profile.getGender());
        System.out.println("Activity Level: " + ActivityLevel.fromLevel(profile.getActivityLevel()).getDescription());
        clear();
    }

    private void viewBMI() {
        clearConsole();
        UserProfile profile = currentSession.getProfile();
        double bmi = CalculatorUtil.calculateBMI(profile.getWeightKG(), profile.getHeightM());
        String bmiCategory = CategoryUtil.getBMICategory(bmi);
        OutputHelper.displayBMIResult(bmi, bmiCategory);
        clear();
    }

    private void viewBMR() {
        clearConsole();
        UserProfile profile = currentSession.getProfile();
        double bmr = CalculatorUtil.calculateBMR(profile.getGender(), profile.getWeightKG(), profile.getHeightCM(), profile.getAge());
        OutputHelper.displayBMRResult(bmr);
        clear();
    }

    private void viewTDEE() {
        clearConsole();
        UserProfile profile = currentSession.getProfile();
        double bmr = CalculatorUtil.calculateBMR(profile.getGender(), profile.getWeightKG(), profile.getHeightCM(), profile.getAge());
        double activityLevelMultiplier = ActivityLevel.fromLevel(profile.getActivityLevel()).getMultiplier();
        double tdee = CalculatorUtil.calculateTDEE(bmr, activityLevelMultiplier);
        OutputHelper.displayTDEEResult(tdee);
        clear();
    }

    private void calculateBodyFatPercentage() {
        clearConsole();
        UserProfile profile = currentSession.getProfile();
        System.out.print("Enter your waist circumference (cm): ");
        double waistCM = InputHelper.readDouble();
        System.out.print("Enter your neck circumference (cm): ");
        double neckCM = InputHelper.readDouble();
        double bodyFatPercentage = 0;
        if (profile.getGender().equalsIgnoreCase("female")) {
            System.out.print("Enter your hip circumference (cm): ");
            double hipCM = InputHelper.readDouble();
            bodyFatPercentage = CalculatorUtil.calculateFemaleBodyFatPercentage(profile.getHeightCM(), waistCM, hipCM, neckCM);
        } else {
            bodyFatPercentage = CalculatorUtil.calculateMaleBodyFatPercentage(profile.getHeightCM(), waistCM,neckCM);
        }
        String bodyFatCategory = CategoryUtil.getBodyFatCategory(bodyFatPercentage, profile.getGender());
        OutputHelper.displayBodyFatPercentageResult(bodyFatPercentage, bodyFatCategory);
        clear();
    }

    private void setWeeklyWeightGoal() {
        clearConsole();
        UserProfile profile = currentSession.getProfile();
        OutputHelper.displayWeightGoalMenu();
        int goalChoice = InputHelper.getIntInRange(1, 3);
        String goalType = getWeightGoalType(goalChoice);
        double rate = 0;
        if (!goalType.equalsIgnoreCase("maintain")) {
            OutputHelper.displayWeightGoalPaceMenu();
            int paceChoice = InputHelper.getIntInRange(1, 2);
            rate = (paceChoice == 1) ? 0.5 : 1;
        }
        double bmr = CalculatorUtil.calculateBMR(profile.getGender(), profile.getWeightKG(), profile.getHeightCM(), profile.getAge());
        double activityLevelMultiplier = ActivityLevel.fromLevel(profile.getActivityLevel()).getMultiplier();
        double tdee = CalculatorUtil.calculateTDEE(bmr, activityLevelMultiplier);
        double calorieAdjustment = rate * 1000;
        double targetCalories = (goalType.equalsIgnoreCase("gain")) ? tdee + calorieAdjustment : tdee - calorieAdjustment;
        WeightGoal goal = new WeightGoal(currentSession.getUsername(), goalType, rate, targetCalories);
        OutputHelper.displayWeightGoalResult(goalType, rate, targetCalories);
        goalManager.saveGoal(goal);
        clear();
    }

    private String getWeightGoalType(int goalChoice) {
        if (goalChoice == 1) return "gain";
        if (goalChoice == 2) return "loss";
        else return "maintain";
    }

    private void updateUserInformation() {
        clearConsole();
        System.out.println("\n*** Enter new information (press Enter to keep current value) ***");
        userManager.updateUserProfile(currentSession);
        clear();
    }

    private void trackCalorieIntake() {
        clearConsole();
        LocalDate today = LocalDate.now();
        WeightGoal goal = goalManager.getGoalForUser(currentSession.getUsername());
        if (goal == null) {
            System.out.println("You haven't set your weight goal yet. Setting one for you right now!");
            setWeeklyWeightGoal();
            goal = goalManager.getGoalForUser(currentSession.getUsername());
        }
        double currentCalories = calorieTracker.getTotalCaloriesForDay(currentSession.getUsername(), today);
        double targetCalories = goal.getTargetCalories();
        OutputHelper.displayCalorieInfo(today, targetCalories, currentCalories);
        Map<String, Double> foodEntries = calorieTracker.getDailyCalorieIntake(currentSession.getUsername(), today);
        if (!foodEntries.isEmpty()) OutputHelper.displayFoodLog(foodEntries);
        OutputHelper.displayFoodItemOptions();
        int option = InputHelper.getIntInRange(1, 2);
        if (option == 1) addFoodItem(today, goal);
        clear();
    }

    private void addFoodItem(LocalDate today, WeightGoal goal) {
        System.out.print("Enter food item name: ");
        String foodItem = InputHelper.readLine();
        System.out.print("Enter calories: ");
        double calories = InputHelper.readDouble();

        calorieTracker.addCalorieIntake(currentSession.getUsername(), today, foodItem, calories);
        double newTotal = calorieTracker.getTotalCaloriesForDay(currentSession.getUsername(), today);
        OutputHelper.displayCalorieIntakeUpdatedInfo(foodItem, calories, newTotal, goal.getTargetCalories());
    }

    private void trackWaterIntake() {
        clearConsole();
        UserProfile profile = currentSession.getProfile();
        LocalDate today = LocalDate.now();
        double currentIntake = waterTracker.getWaterIntake(currentSession.getUsername(), today);
        double recommendedIntake = waterTracker.getRecommendedIntake(profile);
        OutputHelper.displayWaterIntakeInfo(today, recommendedIntake, currentIntake);
        OutputHelper.displayWaterIntakeOptions();
        int option = InputHelper.getIntInRange(1, 2);
        if (option == 1) addWaterIntake(today, recommendedIntake);
        clear();
    }

    private void addWaterIntake(LocalDate date, double recommendedIntake) {
        System.out.print("Enter amount of water (ml): ");
        double amount = InputHelper.readDouble();
        waterTracker.addWaterIntake(currentSession.getUsername(), date, amount);
        double newCurrentIntake = waterTracker.getWaterIntake(currentSession.getUsername(), date);
        OutputHelper.displayWaterIntakeUpdatedInfo(newCurrentIntake, recommendedIntake);
    }

    public void clear() {
        System.out.println("\n\nPress Enter to continue...");
        InputHelper.readLine();
        clearConsole();
    }

    private void clearConsole(){
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
