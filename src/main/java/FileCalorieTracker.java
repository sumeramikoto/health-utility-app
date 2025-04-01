import java.io.*;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

public class CalorieTracker {
    private final String CALORIE_INTAKE_CSV = "calorie_intake.csv";

    public CalorieTracker() {
        initializeFile();
    }

    private void initializeFile() {
        try {
            File file = new File(CALORIE_INTAKE_CSV);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.err.println("Error initializing calorie intake file: " + e.getMessage());
        }
    }

    public void addCalorieIntake(String username, LocalDate date, String foodItem, double calories) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CALORIE_INTAKE_CSV, true))) {
            writer.write(username + "," + date.toString() + "," + foodItem + "," + calories);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to calorie intake file: " + e.getMessage());
        }
    }

    public Map<String, Double> getDailyCalorieIntake(String username, LocalDate date) {
        Map<String, Double> foodEntries = new LinkedHashMap<>();
        String dateStr = date.toString();

        try (BufferedReader reader = new BufferedReader(new FileReader(CALORIE_INTAKE_CSV))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4 && parts[0].equals(username) && parts[1].equals(dateStr)) {
                    String foodItem = parts[2];
                    double calories = Double.parseDouble(parts[3]);
                    foodEntries.put(foodItem, calories);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading calorie intake file: " + e.getMessage());
        }

        return foodEntries;
    }

    public double getTotalCaloriesForDay(String username, LocalDate date) {
        Map<String, Double> entries = getDailyCalorieIntake(username, date);
        double total = 0;

        for (double calories : entries.values()) {
            total += calories;
        }

        return total;
    }
}
