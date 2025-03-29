public class BMICalculator {
    public static double calculateBMI(User user) {
        double weightKG = user.getWeightKG();
        double heightM = user.getHeightCM() / 100;

        return weightKG / (heightM * heightM);
    }
}
