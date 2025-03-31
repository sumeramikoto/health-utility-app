public class UserProfile {
    private int age;
    private double heightM;
    private double weightKG;
    private final String gender;
    private int activityLevel;

    public UserProfile(int age, double heightM, double weightKG, String gender, int activityLevel) {
        this.age = age;
        this.heightM = heightM;
        this.weightKG = weightKG;
        this.gender = gender;
        this.activityLevel = activityLevel;
    }

    public int getAge() {
        return age;
    }

    public double getHeightM() {
        return heightM;
    }

    public double getHeightCM() {
        return heightM * 100;
    }

    public double getWeightKG() {
        return weightKG;
    }

    public String getGender() {
        return gender;
    }

    public int getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(int activityLevel) {
        this.activityLevel = activityLevel;
    }

    public void updateProfile(int age, double heightM, double weightKG, int activityLevel) {
        this.age = age;
        this.heightM = heightM;
        this.weightKG = weightKG;
        this.activityLevel = activityLevel;
    }
}
