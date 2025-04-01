public interface IWeightGoalManager {
    public void saveGoal(WeightGoal goal);
    public WeightGoal getGoalForUser(String username);
}
