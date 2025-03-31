import java.io.IOException;

public class UIHelper {
    private final AuthService authService;

    public UIHelper() {
        this.authService = new AuthService();
    }

    public UserSession login() {
        System.out.print("Enter Your Username: ");
        String username = InputHelper.readLine();
        System.out.print("Enter Password: ");
        String password = InputHelper.readLine();
        try {
            UserCredentials credentials = authService.authenticate(username, password);
            if (credentials != null) {
                UserProfile profile = authService.getUserProfile(username);
                UserSession currentSession = new UserSession(credentials, profile);
                System.out.println("Login successful!");
                return currentSession;
            } else {
                System.out.println("Invalid username or password.");
            }
        } catch (IOException e) {
            System.out.println("Error during login: " + e.getMessage());
        }
        return login();
    }

    public UserSession register() {
        System.out.print("Username: ");
        String username = InputHelper.readLine();
        System.out.print("Password: ");
        String password = InputHelper.readLine();

        System.out.println("\nLet's set up your profile:");
        System.out.print("Age: ");
        int age = InputHelper.readInt();
        System.out.print("Height (M): ");
        double height = InputHelper.readDouble();
        System.out.print("Weight (KG): ");
        double weight = InputHelper.readDouble();

        System.out.print("Gender (Male/Female): ");
        String gender = InputHelper.getGenderInput();

        OutputHelper.displayActivityLevelMenu();
        int activityLevel = InputHelper.getActivityLevelInput();

        UserProfile profile = new UserProfile(age, height, weight, gender, activityLevel);
        UserCredentials credentials = new UserCredentials(username, password);

        try {
            boolean success = authService.registerUser(credentials, profile);
            if (success) {
                UserSession currentSession = new UserSession(credentials, profile);
                System.out.println("Registration successful!");
                return currentSession;
            } else {
                System.out.println("Username already exists. Please try another one.");
            }
        } catch (IOException e) {
            System.out.println("Error during registration: " + e.getMessage());
        }
        return register();
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
