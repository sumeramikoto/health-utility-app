import java.time.LocalDate;
import java.util.Map;

public interface ICalorieTracker {
    public void addCalorieIntake(String username, LocalDate date, String foodItem, double calories);
    public Map<String, Double> getDailyCalorieIntake(String username, LocalDate date);
    public double getTotalCaloriesForDay(String username, LocalDate date);
}
