import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WeightGoalManager {
    private final String GOALS_CSV = "goals.csv";
    private static WeightGoalManager instance;

    private WeightGoalManager() {
        initializeFile();
    }

    public static WeightGoalManager getInstance() {
        if (instance == null) {
            instance = new WeightGoalManager();
        }
        return instance;
    }

    private void initializeFile() {
        try {
            File file = new File(GOALS_CSV);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.err.println("Error initializing weight goals file: " + e.getMessage());
        }
    }

    public void saveGoal(WeightGoal goal) {
        List<String> entries = new ArrayList<>();
        boolean updated = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(GOALS_CSV))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4 && parts[0].equals(goal.getUsername())) {
                    entries.add(goal.getUsername() + "," + goal.getType() + "," + goal.getRate() + "," + goal.getTargetCalories());
                    updated = true;
                } else {
                    entries.add(line);
                }
            }
            addNewGoalIfNotUpdated(goal, updated, entries);
        } catch (IOException e) {
            System.err.println("Error reading weight goals file: " + e.getMessage());
            return;
        }
        writeGoalToFile(entries);
    }

    private void addNewGoalIfNotUpdated(WeightGoal goal, boolean updated, List<String> entries) {
        if (!updated) {
            entries.add(goal.getUsername() + "," + goal.getType() + "," + goal.getRate() + "," + goal.getTargetCalories());
        }
    }

    private void writeGoalToFile(List<String> entries) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(GOALS_CSV))) {
            for (String entry : entries) {
                writer.write(entry);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to weight goals file: " + e.getMessage());
        }
    }

    public WeightGoal getGoalForUser(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader(GOALS_CSV))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4 && parts[0].equals(username)) {
                    String goalType = parts[1];
                    double rate = Double.parseDouble(parts[2]);
                    double targetCalories = Double.parseDouble(parts[3]);
                    return new WeightGoal(username, goalType, rate, targetCalories);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading weight goals file: " + e.getMessage());
        }
        return null;
    }
}
