import java.time.LocalDate;

public interface IWaterTracker {
    public void addWaterIntake(String username, LocalDate date, double amount);
    public double getWaterIntake(String username, LocalDate date);
    public double getRecommendedIntake(UserProfile profile);
}
