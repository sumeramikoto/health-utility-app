public class User {
    private String username;
    private int age;
    private double heightCM;
    private double weightKG;

    public User(String username, int age, double heightCM, double weightKG) {
        this.username = username;
        this.age = age;
        this.heightCM = heightCM;
        this.weightKG = weightKG;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeightCM() {
        return heightCM;
    }

    public void setHeightCM(double heightCM) {
        this.heightCM = heightCM;
    }

    public double getWeightKG() {
        return weightKG;
    }

    public void setWeightKG(double weightKG) {
        this.weightKG = weightKG;
    }
}
