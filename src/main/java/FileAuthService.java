import java.io.*;
import java.util.*;

public class FileAuthService implements IAuthService {
    private final String USERS_CSV = "users.csv";
    private final String PROFILES_CSV = "profiles.csv";

    public FileAuthService() {
        initializeFiles();
    }

    private void initializeFiles() {
        try {
            File usersFileObj = new File(USERS_CSV);
            if (!usersFileObj.exists()) {
                usersFileObj.createNewFile();
            }

            File profilesFileObj = new File(PROFILES_CSV);
            if (!profilesFileObj.exists()) {
                profilesFileObj.createNewFile();
            }
        } catch (IOException e) {
            System.err.println("Error initializing files: " + e.getMessage());
        }
    }

    @Override
    public UserCredentials authenticate(String username, String password) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_CSV))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2 && parts[0].equals(username) && parts[1].equals(password)) {
                    return new UserCredentials(username, password);
                }
            }
        }
        return null;
    }

    @Override
    public boolean registerUser(UserCredentials user, UserProfile profile) throws IOException {
        if (usernameAlreadyExists(user)) {
            return false;
        }

        addUserCredentialsToFile(user);
        addUserProfileToFile(user, profile);
        return true;
    }

    private void addUserProfileToFile(UserCredentials user, UserProfile profile) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PROFILES_CSV, true))) {
            writer.write(user.getUsername() + "," +
                    profile.getAge() + "," +
                    profile.getHeightM() + "," +
                    profile.getWeightKG() + "," +
                    profile.getGender() + "," +
                    profile.getActivityLevel());
            writer.newLine();
        }
    }

    private void addUserCredentialsToFile(UserCredentials user) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_CSV, true))) {
            writer.write(user.getUsername() + "," + user.getPassword());
            writer.newLine();
        }
    }

    private boolean usernameAlreadyExists(UserCredentials user) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_CSV))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 0 && parts[0].equals(user.getUsername())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public UserProfile getUserProfile(String username) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(PROFILES_CSV))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5 && parts[0].equals(username)) {
                    int age = Integer.parseInt(parts[1]);
                    double height = Double.parseDouble(parts[2]);
                    double weight = Double.parseDouble(parts[3]);
                    String gender = parts[4];
                    int activityLevel = Integer.parseInt(parts[5]);
                    return new UserProfile(age, height, weight, gender, activityLevel);
                }
            }
        }
        return null;
    }

    @Override
    public boolean updateUserProfile(String username, UserProfile profile) throws IOException {
        List<String> profiles = new ArrayList<>();
        boolean found = isProfileFound(username, profile, profiles);
        if (!found) {
            return false;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PROFILES_CSV))) {
            for (String profile_line : profiles) {
                writer.write(profile_line);
                writer.newLine();
            }
        }
        return true;
    }

    private boolean isProfileFound(String username, UserProfile profile, List<String> profiles) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(PROFILES_CSV))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if ((parts.length >= 5) && parts[0].equals(username)) {
                    profiles.add(username + "," + profile.getAge() + "," + profile.getHeightM() + "," + profile.getWeightKG() + "," + profile.getGender() + "," + profile.getActivityLevel());
                    return true;
                } else {
                    profiles.add(line);
                }
            }
        }
        return false;
    }
}