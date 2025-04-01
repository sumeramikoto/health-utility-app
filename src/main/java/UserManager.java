import java.io.IOException;

public class UserManager {
    private final IAuthService authService;

    public UserManager(IAuthService authService) {
        this.authService = authService;
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

    public void updateUserProfile(UserSession session) {
        UserProfile profile = session.getProfile();
        System.out.print("Age [" + profile.getAge() + "]: ");
        String ageInput = InputHelper.readLine();
        int age = ageInput.isEmpty() ? profile.getAge() : Integer.parseInt(ageInput);

        System.out.print("Height (m) [" + profile.getHeightM() + "]: ");
        String heightInput = InputHelper.readLine();
        double height = heightInput.isEmpty() ? profile.getHeightM() : Double.parseDouble(heightInput);

        System.out.print("Weight (kg) [" + profile.getWeightKG() + "]: ");
        String weightInput = InputHelper.readLine();
        double weight = weightInput.isEmpty() ? profile.getWeightKG() : Double.parseDouble(weightInput);

        System.out.println("Current Activity Level: " + ActivityLevel.fromLevel(profile.getActivityLevel()).getDescription());
        OutputHelper.displayActivityLevelMenu();
        String activityLevelInput = InputHelper.readLine();
        int activityLevel = activityLevelInput.isEmpty() ? profile.getActivityLevel() : Integer.parseInt(activityLevelInput);
        if (activityLevel < 1 || activityLevel > ActivityLevel.values().length) {
            System.out.print("Invalid input. Please try again: ");
            activityLevel = InputHelper.getActivityLevelInput();
        }
        profile.updateProfile(age, height, weight, activityLevel);
        updateUserInformationInFile(session.getUsername(), profile);
    }

    private void updateUserInformationInFile(String username, UserProfile profile) {
        try {
            boolean success = authService.updateUserProfile(username, profile);
            if (success) {
                System.out.println("Profile updated successfully!");
            } else {
                System.out.println("Failed to update profile.");
            }
        } catch (IOException e) {
            System.out.println("Error updating profile: " + e.getMessage());
        }
    }
}
