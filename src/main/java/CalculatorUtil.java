public class CalculatorUtil {
    private static final double MALE_BMR_CONSTANT = 88.36;
    private static final double MALE_WEIGHT_MULTIPLIER = 13.4;
    private static final double MALE_HEIGHT_MULTIPLIER = 4.8;
    private static final double MALE_AGE_MULTIPLIER = 5.7;

    private static final double FEMALE_BMR_CONSTANT = 447.6;
    private static final double FEMALE_WEIGHT_MULTIPLIER = 9.2;
    private static final double FEMALE_HEIGHT_MULTIPLIER = 3.1;
    private static final double FEMALE_AGE_MULTIPLIER = 4.3;

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
        if (gender.equalsIgnoreCase("male")) return MALE_BMR_CONSTANT + (MALE_WEIGHT_MULTIPLIER * weightKG) + (MALE_HEIGHT_MULTIPLIER * heightCM) - (MALE_AGE_MULTIPLIER * age);
        else return FEMALE_BMR_CONSTANT + (FEMALE_WEIGHT_MULTIPLIER * weightKG) + (FEMALE_HEIGHT_MULTIPLIER * heightCM) - (FEMALE_AGE_MULTIPLIER * age);
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
