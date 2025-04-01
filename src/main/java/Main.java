public class Main {
    public static void main(String[] args) {
        IAuthService authService = new FileAuthService();
        IWeightGoalManager goalManager = new FileWeightGoalManager();
        ICalorieTracker calorieTracker = new FileCalorieTracker();
        IWaterTracker waterTracker = new FileWaterTracker();

        UserManager userManager = new UserManager(authService);

        UserInterface ui = new UserInterface(userManager, goalManager, calorieTracker, waterTracker);
        ui.start();
    }
}
