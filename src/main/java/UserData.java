abstract class User {
    private String username;
    private int age;
    private double heightM;
    private double weightKG;


    public User(String username, int age, double heightM, double weightKG, double neckCM, double waistCM) {
        this.username = username;
        this.age = age;
        this.heightM = heightM;
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

    public double getHeightM() {
        return heightM;
    }

    public double getHeightCM() {
        return heightM * 100;
    }

    public void setHeightM(double heightM) {
        this.heightM = heightM;
    }

    public double getWeightKG() {
        return weightKG;
    }

    public void setWeightKG(double weightKG) {
        this.weightKG = weightKG;
    }

    public double getBMI() {
        return CalculatorUtil.calculateBMI(this);
    }

    public abstract double getBMR();

    public double getTDEE(double activityLevelMultiplier) {
        return getBMR() * activityLevelMultiplier;
    }

    public abstract double getBodyFatPercentage();

    public double getNeckCM() {
        return neckCM;
    }

    public void setNeckCM(double neckCM) {
        this.neckCM = neckCM;
    }

    public double getWaistCM() {
        return waistCM;
    }

    public void setWaistCM(double waistCM) {
        this.waistCM = waistCM;
    }
}
