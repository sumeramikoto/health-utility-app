import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserInterface {
    private final UIHelper uiHelper;
    private UserSession currentSession;

    public UserInterface(UIHelper uiHelper) {
        this.uiHelper = uiHelper;
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
        MenuDisplays.displayAuthMenu();
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
        MenuDisplays.displayMainMenu(mainMenuTitle);
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
        System.out.println("=== BMI Calculation ===");
        System.out.printf("Your BMI is %.2f\n", bmi);
        System.out.println("Category: " + bmiCategory);
        uiHelper.clear();
    }

    private void viewBMR() {
        UserProfile profile = currentSession.getProfile();
        double bmr = CalculatorUtil.calculateBMR(profile.getGender(), profile.getWeightKG(), profile.getHeightCM(), profile.getAge());
        System.out.println("=== BMR Calculation ===");
        System.out.printf("Your Basal Metabolic Rate (BMR) is %.2f\n", bmr);
        System.out.println("This is the number of calories your body needs to maintain basic functions at rest.");
        uiHelper.clear();
    }

    private void viewTDEE() {
        UserProfile profile = currentSession.getProfile();
        double bmr = CalculatorUtil.calculateBMR(profile.getGender(), profile.getWeightKG(), profile.getHeightCM(), profile.getAge());
        double activityLevelMultiplier = ActivityLevel.fromLevel(profile.getActivityLevel()).getMultiplier();
        double tdee = CalculatorUtil.calculateTDEE(bmr, activityLevelMultiplier);
        System.out.println("=== TDEE Calculation ===");
        System.out.printf("Your Total Daily Energy Expenditure (TDEE) is %.2f\n", tdee);
        System.out.println("This is the estimated number of calories you burn per day based on your activity level.");
        uiHelper.clear();
    }

    private void calculateBodyFatPercentage() {
        UserProfile profile = currentSession.getProfile();
        System.out.println("=== Body Fat Percentage Calculation ===");
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
        System.out.printf("Your estimated body fat percentage is %.2f%%\n", bodyFatPercentage);
        System.out.println("Category: " + bodyFatCategory);
        uiHelper.clear();
    }
}
