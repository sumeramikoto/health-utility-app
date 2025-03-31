public class WeightGoal {
    private final String username;
    private final String type;
    private final double rate;
    private final double targetCalories;

    public WeightGoal(String username, String type, double rate, double targetCalories) {
        this.username = username;
        this.type = type;
        this.rate = rate;
        this.targetCalories = targetCalories;
    }

    public String getUsername() {
        return username;
    }

    public String getType() {
        return type;
    }

    public double getRate() {
        return rate;
    }

    public double getTargetCalories() {
        return targetCalories;
    }
}
