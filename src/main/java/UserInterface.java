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
}
