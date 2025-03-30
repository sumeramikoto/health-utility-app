public class CalculatorUtil {
    private static final double MALE_BMR_CONSTANT = 5;
    private static final double FEMALE_BMR_CONSTANT = 161;
    private static final double BMR_WEIGHT_CONSTANT = 10;
    private static final double BMR_HEIGHT_CONSTANT = 6.25;
    private static final double BMR_AGE_CONSTANT = 5;

    private static final double BODY_FAT_CONSTANT1 = 495;
    private static final double BODY_FAT_CONSTANT2 = 450;

    private static final double MALE_BODY_FAT_CONSTANT1 = 1.0324;
    private static final double MALE_BODY_FAT_CONSTANT2 = 0.19077;
    private static final double MALE_BODY_FAT_CONSTANT3 = 0.15456;

    private static final double FEMALE_BODY_FAT_CONSTANT1 = 1.29579;
    private static final double FEMALE_BODY_FAT_CONSTANT2 = 0.35004;
    private static final double FEMALE_BODY_FAT_CONSTANT3 = 0.22100;

    public static double calculateBMI(double weightKG, double heightM) {
        return weightKG / Math.pow(heightM, 2);
    }

    public static double calculateBMR(String gender, double weightKG, double heightCM, int age) {
        if (gender.equalsIgnoreCase("male")) return BMR_WEIGHT_CONSTANT * weightKG + BMR_HEIGHT_CONSTANT * heightCM - BMR_AGE_CONSTANT * age + MALE_BMR_CONSTANT;
        else return BMR_WEIGHT_CONSTANT * weightKG + BMR_HEIGHT_CONSTANT * heightCM - BMR_AGE_CONSTANT * age - FEMALE_BMR_CONSTANT;
    }

    public static double calculateTDEE(double bmr, double activityLevelMultiplier) {
        return bmr * activityLevelMultiplier;
    }

    public static double calculateMaleBodyFatPercentage(double heightCM, double waistCM, double neckCM) {
        return BODY_FAT_CONSTANT1 / (MALE_BODY_FAT_CONSTANT1 - MALE_BODY_FAT_CONSTANT2 * Math.log10(waistCM - neckCM) + MALE_BODY_FAT_CONSTANT3 * Math.log10(heightCM)) - BODY_FAT_CONSTANT2;
    }

    public static double calculateFemaleBodyFatPercentage(double heightCM, double waistCM, double hipCM, double neckCM) {
        return BODY_FAT_CONSTANT1 / (FEMALE_BODY_FAT_CONSTANT1 - FEMALE_BODY_FAT_CONSTANT2 * Math.log10(waistCM + hipCM - neckCM) + FEMALE_BODY_FAT_CONSTANT3 * Math.log10(heightCM)) - BODY_FAT_CONSTANT2;
    }
}
