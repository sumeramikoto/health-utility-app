import java.time.LocalDate;
import java.util.Map;

public interface ICalorieTracker {
    void addCalorieIntake(String username, LocalDate date, String foodItem, double calories);
    Map<String, Double> getDailyCalorieIntake(String username, LocalDate date);
    double getTotalCaloriesForDay(String username, LocalDate date);
}
