public class CategoryUtil {
    public static String getBMICategory(double bmi) {
        if (bmi < 18.5) return "Underweight";
        if (bmi < 25) return "Normal weight";
        if (bmi < 30) return "Overweight";
        return "Obese";
    }

    public static String getBodyFatCategory(double bodyFat, String gender) {
        if (gender.equals("male")) {
            if (bodyFat < 6) return "Essential fat";
            if (bodyFat < 14) return "Athletic";
            if (bodyFat < 18) return "Fitness";
            if (bodyFat < 25) return "Average";
        } else {
            if (bodyFat < 14) return "Essential fat";
            if (bodyFat < 21) return "Athletic";
            if (bodyFat < 25) return "Fitness";
            if (bodyFat < 32) return "Average";
        }
        return "Obese";
    }
}
