abstract class UserData {
    private int age;
    private double heightM;
    private double weightKG;
    private final String gender;
    

    public UserData(int age, double heightM, double weightKG, String gender) {
        this.age = age;
        this.heightM = heightM;
        this.weightKG = weightKG;
        this.gender = gender;
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

    public String getGender() {
        return gender;
    }
}
