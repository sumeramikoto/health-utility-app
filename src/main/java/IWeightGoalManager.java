public interface IWeightGoalManager {
    void saveGoal(WeightGoal goal);
    WeightGoal getGoalForUser(String username);
}
