public enum ActivityLevel {
    SEDENTARY(1, 1.2, "Sedentary (little or no exercise)"),
    LIGHTLY_ACTIVE(2, 1.375, "Lightly active (light exercise/sports 1-3 days/week)"),
    MODERATELY_ACTIVE(3, 1.55, "Moderately active (moderate exercise/sports 3-5 days/week)"),
    VERY_ACTIVE(4, 1.725, "Very active (hard exercise/sports 6-7 days a week)"),
    EXTRA_ACTIVE(5, 1.9, "Extra active (very hard exercise, physical job or training twice a day)");

    private final int level;
    private final double multiplier;
    private final String description;

    ActivityLevel(int level, double multiplier, String description) {
        this.level = level;
        this.multiplier = multiplier;
        this.description = description;
    }

    public int getLevel() {
        return level;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public String getDescription() {
        return description;
    }

    public static ActivityLevel fromLevel(int level) {
        for (ActivityLevel activityLevel : values()) {
            if (activityLevel.level == level) {
                return activityLevel;
            }
        }
        throw new IllegalArgumentException("Invalid activity level: " + level);
    }
}
