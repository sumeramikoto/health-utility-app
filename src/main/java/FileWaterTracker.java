import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WaterTracker {
    private final String WATER_INTAKE_CSV = "water_intake.csv";
    private final double STANDARD_WATER_RECOMMENDATION_ML_PER_KG = 35;

    public WaterTracker() {
        initializeFile();
    }

    private void initializeFile() {
        try {
            File file = new File(WATER_INTAKE_CSV);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.err.println("Error initializing water intake file: " + e.getMessage());
        }
    }

    public void addWaterIntake(String username, LocalDate date, double amount) {
        String dateStr = date.toString();
        double currentIntake = getWaterIntake(username, date);
        double newTotal = currentIntake + amount;
        List<String> entries = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(WATER_INTAKE_CSV))) {
            readWaterIntakeFile(username, reader, dateStr, entries, newTotal);
        } catch (IOException e) {
            System.err.println("Error reading water intake file: " + e.getMessage());
            return;
        }

        writeToWaterIntakeFile(entries);
    }

    private static void readWaterIntakeFile(String username, BufferedReader reader, String dateStr, List<String> entries, double newTotal) throws IOException {
        boolean updatedExistingEntryForSameUser = false;
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 3 && parts[0].equals(username) && parts[1].equals(dateStr)) {
                entries.add(username + "," + dateStr + "," + newTotal);
                updatedExistingEntryForSameUser = true;
            } else {
                entries.add(line);
            }
        }

        if (!updatedExistingEntryForSameUser) {
            entries.add(username + "," + dateStr + "," + newTotal);
        }
    }

    private void writeToWaterIntakeFile(List<String> entries) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(WATER_INTAKE_CSV))) {
            for (String entry : entries) {
                writer.write(entry);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to water intake file: " + e.getMessage());
        }
    }

    public double getWaterIntake(String username, LocalDate date) {
        String dateStr = date.toString();
        try (BufferedReader reader = new BufferedReader(new FileReader(WATER_INTAKE_CSV))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3 && parts[0].equals(username) && parts[1].equals(dateStr)) {
                    return Double.parseDouble(parts[2]);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading water intake file: " + e.getMessage());
        }
        return 0.0;
    }

    public double getRecommendedIntake(UserProfile profile) {
        double weightInKg = profile.getWeightKG();
        return weightInKg * STANDARD_WATER_RECOMMENDATION_ML_PER_KG;
    }
}
