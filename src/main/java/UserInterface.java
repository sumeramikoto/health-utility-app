import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserInterface {
    private final UIHelper uiHelper;
    private UserSession currentSession;
    private final WeightGoalManager goalManager;

    public UserInterface() {
        this.uiHelper = new UIHelper();
        this.goalManager = new WeightGoalManager();
    }

    public void start() {
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
                currentSession = uiHelper.login();
                return true;
            case 2:
                currentSession = uiHelper.register();
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
//            case 7 -> trackCalorieIntake();
//            case 8 -> trackWaterIntake();
//            case 9 -> updateUserInformation();
            case 10 -> {
                currentSession = null;
                System.out.println("Signed out successfully.");
            }
            default -> System.out.println("Invalid option. Please try again.");
        }
    }

    private void viewUserProfile() {
        UserProfile profile = currentSession.getProfile();
        System.out.println("=== " + currentSession.getUsername() + "'s Profile ===");
        System.out.println("Age: " + profile.getAge());
        System.out.println("Height: " + profile.getHeightM() + " m");
        System.out.println("Weight: " + profile.getWeightKG() + " kg");
        System.out.println("Gender: " + profile.getGender());
        System.out.println("Activity Level: " + ActivityLevel.fromLevel(profile.getActivityLevel()).getDescription());
        uiHelper.clear();
    }

    private void viewBMI() {
        UserProfile profile = currentSession.getProfile();
        double bmi = CalculatorUtil.calculateBMI(profile.getWeightKG(), profile.getHeightM());
        String bmiCategory = CategoryUtil.getBMICategory(bmi);
        OutputHelper.displayBMIResult(bmi, bmiCategory);
        uiHelper.clear();
    }

    private void viewBMR() {
        UserProfile profile = currentSession.getProfile();
        double bmr = CalculatorUtil.calculateBMR(profile.getGender(), profile.getWeightKG(), profile.getHeightCM(), profile.getAge());
        OutputHelper.displayBMRResult(bmr);
        uiHelper.clear();
    }

    private void viewTDEE() {
        UserProfile profile = currentSession.getProfile();
        double bmr = CalculatorUtil.calculateBMR(profile.getGender(), profile.getWeightKG(), profile.getHeightCM(), profile.getAge());
        double activityLevelMultiplier = ActivityLevel.fromLevel(profile.getActivityLevel()).getMultiplier();
        double tdee = CalculatorUtil.calculateTDEE(bmr, activityLevelMultiplier);
        OutputHelper.displayTDEEResult(tdee);
        uiHelper.clear();
    }

    private void calculateBodyFatPercentage() {
        UserProfile profile = currentSession.getProfile();
        System.out.print("Enter your waist circumference (cm): ");
        double waistCM = InputHelper.readDouble();
        System.out.print("Enter your neck circumference (cm): ");
        double neckCM = InputHelper.readDouble();
        double bodyFatPercentage = CalculatorUtil.calculateMaleBodyFatPercentage(profile.getHeightCM(), waistCM, neckCM);
        if (profile.getGender().equalsIgnoreCase("female")) {
            System.out.print("Enter your hip circumference (cm): ");
            double hipCM = InputHelper.readDouble();
            bodyFatPercentage = CalculatorUtil.calculateFemaleBodyFatPercentage(profile.getHeightCM(), waistCM, hipCM, neckCM);
        }
        String bodyFatCategory = CategoryUtil.getBodyFatCategory(bodyFatPercentage, profile.getGender());
        OutputHelper.displayBodyFatPercentageResult(bodyFatPercentage, bodyFatCategory);
        uiHelper.clear();
    }

    private void setWeeklyWeightGoal() {
        UserProfile profile = currentSession.getProfile();
        OutputHelper.displayWeightGoalMenu();
        int goalChoice = InputHelper.getInt1or2();
        String goalType = (goalChoice == 1) ? "gain" : "loss";
        OutputHelper.displayWeightGoalPaceMenu();
        int paceChoice = InputHelper.getInt1or2();
        double rate = (paceChoice == 1) ? 0.5 : 1;
        double bmr = CalculatorUtil.calculateBMR(profile.getGender(), profile.getWeightKG(), profile.getHeightCM(), profile.getAge());
        double activityLevelMultiplier = ActivityLevel.fromLevel(profile.getActivityLevel()).getMultiplier();
        double tdee = CalculatorUtil.calculateTDEE(bmr, activityLevelMultiplier);
        double calorieAdjustment = rate * 1000;
        double targetCalories = (goalType.equalsIgnoreCase("gain")) ? tdee + calorieAdjustment : tdee - calorieAdjustment;
        WeightGoal goal = new WeightGoal(currentSession.getUsername(), goalType, rate, targetCalories);
        OutputHelper.displayWeightGoalResult(goalType, rate, targetCalories);
        goalManager.saveGoal(goal);
        uiHelper.clear();
    }


}
