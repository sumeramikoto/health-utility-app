import java.time.LocalDate;

public interface IWaterTracker {
    void addWaterIntake(String username, LocalDate date, double amount);
    double getWaterIntake(String username, LocalDate date);
    double getRecommendedIntake(UserProfile profile);
}
